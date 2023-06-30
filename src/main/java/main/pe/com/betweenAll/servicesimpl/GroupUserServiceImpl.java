package main.pe.com.betweenAll.servicesimpl;

import main.pe.com.betweenAll.dtos.DTOGroupParticipantsSummary;
import main.pe.com.betweenAll.dtos.DTOGroupUserSummary;
import main.pe.com.betweenAll.dtos.DTOMyGroupsSummary;
import main.pe.com.betweenAll.entities.Group;
import main.pe.com.betweenAll.entities.GroupUser;
import main.pe.com.betweenAll.entities.SocialEvent;
import main.pe.com.betweenAll.entities.User;
import main.pe.com.betweenAll.repositories.GroupRepository;
import main.pe.com.betweenAll.repositories.GroupUserRepository;
import main.pe.com.betweenAll.repositories.UserRepository;
import main.pe.com.betweenAll.services.GroupUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.module.ResolutionException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GroupUserServiceImpl implements GroupUserService {

    @Autowired
    GroupUserRepository groupUserRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    UserRepository userRepository;

    @Transactional
    public GroupUser listById(Long id) {
        GroupUser groupUser;
        groupUser = groupUserRepository.findById(id).orElseThrow(()->new ResolutionException("Not found an GroupUser with id="+id));
        groupUser.getGroup().setGroupUserList(null);
        groupUser.getUser().setGroupUserList(null);
        return null;
    }

    @Transactional
    public GroupUser save(GroupUser groupUser, Long idGroup, Long idUser){
        Group group = groupRepository.findById(idGroup).get();
        User user = userRepository.findById(idUser).get();

        group.setGroupUserList(null);
        group.getCategory().setGroupList(null);
        group.getCategory().setSocialEventList(null);
        group.getCategory().setUserCategoryList(null);
        user.setAuthorityList(null);
        user.setCardList(null);
        user.setAuthorityList(null);
        user.setGroupList(null);
        user.setGroupUserList(null);
        user.setUserCategoryList(null);
        user.setSocialEventList(null);
        user.setPurchaseList(null);


        groupUser.setGroup(group);
        groupUser.setUser(user);
        GroupUser newGroupUser = groupUserRepository.save(groupUser);
        return newGroupUser;
    }

    @Transactional
    public void delete(Long idUser, boolean forced){
        List<GroupUser> groupUserList = groupUserRepository.findAll();
        GroupUser groupUser = new GroupUser();
        for(GroupUser gU: groupUserList) {
            if(gU.getUser().getId().equals(idUser)){
                groupUser=gU;
            }
        }
        groupUserRepository.delete(groupUser);
    }
    public void deleteByUserAndGroup(Long idUser,Long idGroup, boolean forced){
        List<GroupUser> groupUserList = groupUserRepository.findAll();
        for(GroupUser s: groupUserList) {
            if (s.getGroup().getId()==idGroup && s.getUser().getId()==idUser){
                Long idGroupUser= s.getId();
                GroupUser groupUser=groupUserRepository.findById(idGroupUser).get();
                groupUserRepository.delete(groupUser);
            }
        }

    }

    @Transactional
    public List<GroupUser> listAll() {
        List<GroupUser> groupUserList = groupUserRepository.findAll();
        for(GroupUser s: groupUserList){
            s.getUser().setSocialEventList(null);
            s.getUser().setGroupUserList(null);
            s.getUser().setPurchaseList(null);
            s.getUser().setUserCategoryList(null);
            s.getUser().setAuthorityList(null);
            s.getUser().setCardList(null);
            s.getUser().setGroupList(null);
            s.getGroup().setCategory(null);
            s.getGroup().setGroupUserList(null);
        }
        return groupUserList;
    }

    @Transactional
    public List<DTOGroupUserSummary> listGroupUserSummary(){
        List<GroupUser>groupUserList=groupUserRepository.findAll();
        List<DTOGroupUserSummary> dtoGroupUserSummaryList = new ArrayList<>();

        for(GroupUser gU: groupUserList) {
            DTOGroupUserSummary dtoGroupUserSummary= new DTOGroupUserSummary(gU.getGroup().getName(),
                    gU.getUser().getName(), gU.getGroup().getId(), gU.getGroup().getId());
            dtoGroupUserSummaryList.add(dtoGroupUserSummary);
        }
        return dtoGroupUserSummaryList;
    }
    @Transactional
    public List<DTOMyGroupsSummary> listMyGroupUserSummary(Long idUser){
        List<GroupUser>groupUserList=groupUserRepository.findAll();
        List<DTOMyGroupsSummary> dtoMyGroupsSummaryList = new ArrayList<>();
        for (GroupUser gU:groupUserList){

            if (gU.getUser().getId()==idUser){//&&
                String imageGroup=gU.getGroup().getImage();
                String nameGroup=gU.getGroup().getName();
                Integer amountParticipants=(int)gU.getGroup().getGroupUserList().stream().count();
                String descriptionGroup=gU.getGroup().getDescription();
                String nameCategory=gU.getGroup().getCategory().getName();
                DTOMyGroupsSummary dtoMyGroupsSummary=new DTOMyGroupsSummary(gU.getGroup().getId(),imageGroup,nameGroup,amountParticipants,descriptionGroup,nameCategory);
                dtoMyGroupsSummaryList.add(dtoMyGroupsSummary);
            }
        }
        return dtoMyGroupsSummaryList;
    }
}
