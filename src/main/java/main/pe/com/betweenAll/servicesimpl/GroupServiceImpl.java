package main.pe.com.betweenAll.servicesimpl;

import main.pe.com.betweenAll.dtos.DTOGroupParticipantsSummary;
import main.pe.com.betweenAll.dtos.DTOGroupSummary;
import main.pe.com.betweenAll.dtos.DTOGroupsJoinnedSummary;

import main.pe.com.betweenAll.entities.Category;

import main.pe.com.betweenAll.entities.Group;
import main.pe.com.betweenAll.entities.GroupUser;
import main.pe.com.betweenAll.entities.User;
import main.pe.com.betweenAll.exceptions.IncompleteDataException;
import main.pe.com.betweenAll.repositories.CategoryRepository;
import main.pe.com.betweenAll.repositories.GroupRepository;
import main.pe.com.betweenAll.repositories.UserRepository;
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
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    UserRepository userRepository;
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
            g.getUser().setGroupUserList(null);
            g.getUser().setGroupList(null);
            g.getUser().setGroupList(null);
            g.getUser().setGroupUserList(null);
            g.getUser().setUserCategoryList(null);
            g.getUser().setSocialEventList(null);
            g.getUser().setPurchaseList(null);
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
        //group.getCategory().setGroupList(null);
        group.setGroupUserList(null);
        group.getCategory().setGroupList(null);
        group.getCategory().setSocialEventList(null);
        group.getCategory().setUserCategoryList(null);
        group.getUser().setAuthorityList(null);
        group.getUser().setCardList(null);
        group.getUser().setAuthorityList(null);
        group.getUser().setGroupList(null);
        group.getUser().setGroupUserList(null);
        group.getUser().setUserCategoryList(null);
        group.getUser().setSocialEventList(null);
        group.getUser().setPurchaseList(null);

        return group;
    }

    @Transactional
    public Group save(Group group, Long idUser, Long idCategory){
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

        //Group newGroup = groupRepository.save(new Group(group.getName(),group.getDescription(),group.getImage(),group.getCategory(),group.getUser()));
        //Category category = categoryRepository.save(new Category(group.getCategory().getName(), group.getCategory().getState()));

        //categoryRepository.save(group.getCategory());
        Category category = categoryRepository.findById(idCategory).get();
        category.setGroupList(null);
        category.setSocialEventList(null);
        category.setUserCategoryList(null);

        group.setCategory(category);

        User user = userRepository.findById(idUser).get();
        user.setGroupUserList(null);
        user.setGroupList(null);
        user.setUserCategoryList(null);
        user.setSocialEventList(null);
        user.setCardList(null);
        user.setPurchaseList(null);

        group.setUser(user);


        //User user = group.getUser();

        Group newGroup = groupRepository.save(group);

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
                Integer amountParticipants = (int) g.getGroupUserList().stream().count();
                String nameGroup=g.getName();
                String nameCategory=g.getCategory().getName();
                DTOGroupsJoinnedSummary dtoGroupsJoinnedSummary=new DTOGroupsJoinnedSummary(idGroup,g.getImage(),nameGroup,amountParticipants,g.getDescription(),nameCategory);
                dtoGroupsJoinnedSummaryList.add(dtoGroupsJoinnedSummary);
            }
        }
        return dtoGroupsJoinnedSummaryList;
    }

    @Transactional
    public List<DTOGroupParticipantsSummary> listGroupParticipantsSummary(){
        List<Group>groupList=groupRepository.findAll();
        List<DTOGroupParticipantsSummary> dtoGroupParticipantsSummaryList = new ArrayList<>();
        for(Group g: groupList){
            Integer amountParticipants = (int) g.getGroupUserList().stream().count();
            String category = g.getCategory().getName();
            //Podria pasar esta lista de usuarios en vez de la lista de GroupUser
            List<User> userList = new ArrayList<>();
            userList = null;

            for(GroupUser groupUser: g.getGroupUserList()){
                groupUser.getUser().setSocialEventList(null);
                groupUser.getUser().setGroupUserList(null);
                groupUser.getUser().setPurchaseList(null);
                groupUser.getUser().setUserCategoryList(null);
                groupUser.getUser().setAuthorityList(null);
                groupUser.getUser().setCardList(null);
            }

            DTOGroupParticipantsSummary dtoGroupParticipantsSummary = new DTOGroupParticipantsSummary(g.getId(),g.getImage(),g.getName(),
                    amountParticipants, g.getDescription(), category, userList);
            dtoGroupParticipantsSummaryList.add(dtoGroupParticipantsSummary);


        }
        return dtoGroupParticipantsSummaryList;
    }

    @Transactional
    public DTOGroupParticipantsSummary groupParticipantsSummary(Long id){

        Group group = groupRepository.findById(id).orElse(null);

        Integer amountParticipants = (int) group.getGroupUserList().stream().count();
        String category = group.getCategory().getName();

        List<User> userList = new ArrayList<>();

        for(GroupUser groupUser: group.getGroupUserList()){
            groupUser.getUser().setSocialEventList(null);
            groupUser.getUser().setGroupUserList(null);
            groupUser.getUser().setPurchaseList(null);
            groupUser.getUser().setUserCategoryList(null);
            groupUser.getUser().setAuthorityList(null);
            groupUser.getUser().setCardList(null);
            groupUser.getUser().setGroupList(null);
            userList.add(groupUser.getUser());
        }

        DTOGroupParticipantsSummary dtoGroupParticipantsSummary = new DTOGroupParticipantsSummary(group.getId(),group.getImage(),group.getName(),
                amountParticipants, group.getDescription(), category, userList);

        return dtoGroupParticipantsSummary;
    }


}
