package main.pe.com.betweenAll.servicesimpl;

import main.pe.com.betweenAll.dtos.DTOGroupSummary;
import main.pe.com.betweenAll.dtos.DTOGroupsJoinnedSummary;
import main.pe.com.betweenAll.entities.Group;
import main.pe.com.betweenAll.exceptions.IncompleteDataException;
import main.pe.com.betweenAll.repositories.GroupRepository;
import main.pe.com.betweenAll.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.module.ResolutionException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    GroupRepository groupRepository;
    @Transactional
    public List<Group> listAll() {
        List<Group> groups;
        groups=groupRepository.findAll();
        for(Group g: groups){
            g.setGroupUserList(null);
            g.getCategory().setGroupList(null);
            g.getCategory().setSocialEventList(null);
            g.getCategory().setUserCategoryList(null);
            g.getUser().setAuthorityList(null);
            g.getUser().setCardList(null);
            g.getUser().setAuthorityList(null);
        }
        return groups;
    }
    @Transactional
    public List<Group> listByName(String name){
        List<Group> groups;
        groups=groupRepository.findBynameContaining(name);
        for(Group g: groups){
            g.getCategory().setGroupList(null);
        }
        return groups;
    }
    @Transactional
    public Group listById(Long id) {
        Group group;
        group = groupRepository.findById(id).orElseThrow(()->new ResolutionException("Not found an Group with id="+id));
        group.getCategory().setGroupList(null);
        return null;
    }

    @Transactional
    public Group save(Group group){
        //EXCEPTIONS
        if(group.getName()==null || group.getName().isEmpty()){
            throw new IncompleteDataException("Group Name can not be null or empty");
        }
        if(group.getDescription()==null || group.getDescription().isEmpty()){
            throw new IncompleteDataException("Group Description can not be null or empty");
        }
        if(group.getImage()==null || group.getImage().isEmpty()){
            throw new IncompleteDataException("Group Image can not be null or empty");
        }
        Group newGroup = groupRepository.save(new Group(group.getName(),group.getDescription(),group.getImage(),group.getCategory(),group.getUser()));
        return newGroup;
    }
    @Transactional
    public void delete(Long id,boolean forced){
        Group group=groupRepository.findById(id).get();
        groupRepository.delete(group);
    }

    @Transactional
    public List<DTOGroupSummary> listGroupSummary(){
        List<Group> groupList = groupRepository.findAll();
        List<DTOGroupSummary> dtoGroupSummaryList = new ArrayList<>();
        for(Group g:groupList){
            String nameCategory = g.getCategory().getName();
            Integer countUsers = (int)g.getGroupUserList().stream().count();

            DTOGroupSummary dtoGroupSummary= new DTOGroupSummary(g.getName(),nameCategory,countUsers);
            dtoGroupSummaryList.add(dtoGroupSummary);
        }
        return dtoGroupSummaryList;
    }
    @Transactional
    public List<DTOGroupsJoinnedSummary> listGroupByUserSummary(Long id){
        List<Group>groupList=groupRepository.findAll();
        List<DTOGroupsJoinnedSummary>dtoGroupsJoinnedSummaryList=new ArrayList<>();
        for (Group g: groupList){
            if(g.getUser().getId()==id){
                Long idGroup=g.getId();
                String nameGroup=g.getName();
                String nameCategory=g.getCategory().getName();
                DTOGroupsJoinnedSummary dtoGroupsJoinnedSummary=new DTOGroupsJoinnedSummary(idGroup,nameGroup,nameCategory);
                dtoGroupsJoinnedSummaryList.add(dtoGroupsJoinnedSummary);
            }

        }
        return dtoGroupsJoinnedSummaryList;
    }
}
