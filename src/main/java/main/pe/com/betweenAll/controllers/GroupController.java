package main.pe.com.betweenAll.controllers;

import main.pe.com.betweenAll.dtos.DTOGroupParticipantsSummary;
import main.pe.com.betweenAll.dtos.DTOGroupSummary;
import main.pe.com.betweenAll.dtos.DTOGroupsJoinnedSummary;
import main.pe.com.betweenAll.entities.Category;
import main.pe.com.betweenAll.entities.Group;
import main.pe.com.betweenAll.services.CategoryService;
import main.pe.com.betweenAll.services.GroupService;
import main.pe.com.betweenAll.servicesimpl.GroupServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api")
public class GroupController {
    @Autowired
    GroupService groupService;
    @Autowired
    CategoryService categoryService;
    @GetMapping("/groups")
    public ResponseEntity<List<Group>> getAllGroups(){
        List<Group> groups = groupService.listAll();
        return new ResponseEntity<List<Group>>(groups,HttpStatus.OK);
    }

    @GetMapping("/groups/{id}")
    public ResponseEntity<Group> getGroupById(@PathVariable("id") Long id){
        Group group = groupService.listById(id);
        return new ResponseEntity<Group>(group, HttpStatus.OK);
    }

    @GetMapping("/groups/name/{name}")
    public ResponseEntity<List<Group>> getGroupsByName(@PathVariable("name") String name){
        List<Group> groups = groupService.listByName(name);
        return new ResponseEntity<List<Group>>(groups,HttpStatus.OK);
    }
    @PostMapping("/groups/{idUser}/{idCategory}")
    public ResponseEntity<Group> createGroup(@RequestBody Group group,  @PathVariable("idUser") Long idUser, @PathVariable("idCategory") Long idCategory){
        Group newgroup = groupService.save(group, idUser, idCategory);
        return new ResponseEntity<Group>(newgroup,HttpStatus.OK);
    }
    @DeleteMapping("/groups/{id}")
    public ResponseEntity<HttpStatus> deleteGroup(@PathVariable("id") Long id){
        groupService.delete(id,true);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/groups/{idUser}/{idCategory}/{id}")
    public ResponseEntity<Group> updateGroup(@RequestBody Group group, @PathVariable("idUser") Long idUser, @PathVariable("idCategory") Long idCategory, @PathVariable("id") Long id){
        Group foundGroup= groupService.listById(id);
        if(group.getName()!=null){
            foundGroup.setName(group.getName());
        };
        if(group.getCategory()!=null){
            foundGroup.setCategory(group.getCategory());
        };
        if(group.getImage()!=null){
            foundGroup.setImage(group.getImage());
        };

        Group updateGroup=groupService.save(foundGroup, idUser, idCategory);
        return new ResponseEntity<Group>(updateGroup,HttpStatus.OK);
    }

    @GetMapping("/groups/summary")
    public ResponseEntity<List<DTOGroupSummary>> getGroupSummay(){
        List<DTOGroupSummary> dtoGroupSummaries =groupService.listGroupSummary();
        return new ResponseEntity<List<DTOGroupSummary>>(dtoGroupSummaries,HttpStatus.OK);
    }


    @GetMapping("/groups/groupsCreatedSummary/{id}")
    public ResponseEntity<List<DTOGroupsJoinnedSummary>> getGroupByUserSummary(@PathVariable("id") Long id) {
        List<DTOGroupsJoinnedSummary> dtoGroupsJoinnedSummaryList = groupService.listGroupByUserSummary(id);
        return new ResponseEntity<List<DTOGroupsJoinnedSummary>>(dtoGroupsJoinnedSummaryList, HttpStatus.OK);
    }
    @GetMapping("/groups/groupsSummary")
    public ResponseEntity<List<DTOGroupParticipantsSummary>> getListGroupParticipantsSummary() {
        List<DTOGroupParticipantsSummary> dtoGroupParticipantsSummaryList = groupService.listGroupParticipantsSummary();
        return new ResponseEntity<List<DTOGroupParticipantsSummary>>(dtoGroupParticipantsSummaryList, HttpStatus.OK);
    }

    @GetMapping("/groups/groupParticipantsSummary/{id}")
    public ResponseEntity<DTOGroupParticipantsSummary> getGroupParticipantsSummary(@PathVariable("id") Long id) {
        DTOGroupParticipantsSummary dtoGroupParticipantsSummary = groupService.groupParticipantsSummary(id);
        return new ResponseEntity<DTOGroupParticipantsSummary>(dtoGroupParticipantsSummary, HttpStatus.OK);

    }
}
