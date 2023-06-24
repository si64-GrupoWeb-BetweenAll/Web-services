package main.pe.com.betweenAll.servicesimpl;

import main.pe.com.betweenAll.dtos.DTOGroupParticipantsSummary;
import main.pe.com.betweenAll.dtos.DTOGroupUserSummary;
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
    public GroupUser save(GroupUser groupUser){
        GroupUser newGroupUser = groupUserRepository.save(new GroupUser(groupUser.getUser(),groupUser.getGroup()));
        return newGroupUser;
    }

    @Transactional
    public void delete(Long id, boolean forced){
        GroupUser groupUser = groupUserRepository.findById(id).get();
        groupUserRepository.delete(groupUser);
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
    public List<DTOGroupParticipantsSummary> listGroupParticipantsSummary(){

        List<GroupUser>groupUserList=groupUserRepository.findAll();

        List<DTOGroupParticipantsSummary> dtoGroupParticipantsSummaryList = new ArrayList<>();

        for(GroupUser gU: groupUserList){
            Integer amountParticipants = (int) gU.getGroup().getGroupUserList().stream().count();
            String category = gU.getGroup().getCategory().getName();

            //Podria pasar esta lista de usuarios en vez de la lista de GroupUser
            List<User> userList = new ArrayList<>();
            userList.add(gU.getUser());

            DTOGroupParticipantsSummary dtoGroupParticipantsSummary = new DTOGroupParticipantsSummary(gU.getGroup().getName(),
                    amountParticipants, gU.getGroup().getDescription(), category, userList);
            dtoGroupParticipantsSummaryList.add(dtoGroupParticipantsSummary);

            gU.getUser().setSocialEventList(null);
            gU.getUser().setGroupUserList(null);
            gU.getUser().setPurchaseList(null);
            gU.getUser().setUserCategoryList(null);
            gU.getUser().setAuthorityList(null);
            gU.getUser().setCardList(null);
            gU.setGroup(null);
        }
        return dtoGroupParticipantsSummaryList;
    }


}
