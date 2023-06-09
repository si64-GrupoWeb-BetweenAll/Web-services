package main.pe.com.betweenAll.servicesimpl;

import main.pe.com.betweenAll.entities.Group;
import main.pe.com.betweenAll.repositories.GroupRepository;
import main.pe.com.betweenAll.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
            g.setCategory(null);
            g.getCategory().setGroupList(null);
        }
        return groups;
    }
    @Transactional
    public List<Group> listByName(String name){
        List<Group> groups;
        groups=groupRepository.findBynameContaining(name);
        for(Group g: groups){
            g.setCategory(null);
        }
        return groups;
    }
    @Transactional
    public Group listById(Long id) {
        return null;
    }

    @Transactional
    public Group save(Group group){
        Group newGroup = groupRepository.save(new Group(group.getName(),group.getDescription(),group.getImage()));
        return newGroup;
    }
    @Transactional
    public void delete(Long id,boolean forced){
        Group group=groupRepository.findById(id).get();
        groupRepository.delete(group);
    }

}
