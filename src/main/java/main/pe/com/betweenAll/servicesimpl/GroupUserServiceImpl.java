package main.pe.com.betweenAll.servicesimpl;

import main.pe.com.betweenAll.entities.GroupUser;
import main.pe.com.betweenAll.repositories.GroupRepository;
import main.pe.com.betweenAll.repositories.GroupUserRepository;
import main.pe.com.betweenAll.repositories.UserRepository;
import main.pe.com.betweenAll.services.GroupUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
        return groupUserList;
    }

}
