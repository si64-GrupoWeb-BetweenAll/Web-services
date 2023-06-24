package main.pe.com.betweenAll.controllers;

import main.pe.com.betweenAll.dtos.DTOGroupParticipantsSummary;
import main.pe.com.betweenAll.dtos.DTOGroupUserSummary;
import main.pe.com.betweenAll.entities.GroupUser;
import main.pe.com.betweenAll.services.GroupUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api")
public class GroupUserController {

    @Autowired
    GroupUserService groupUserService;

    @PostMapping("/groupUsers")
    public ResponseEntity<GroupUser> createGroupUser(@RequestBody GroupUser groupUser){
        GroupUser saveGroupUser = groupUserService.save(groupUser);
        return new ResponseEntity<GroupUser>(saveGroupUser, HttpStatus.CREATED);
    }
    @DeleteMapping("/groupUsers/{id}")
    public ResponseEntity<HttpStatus> deleteGroupUser(@PathVariable("id") Long id){
        groupUserService.delete(id,true);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/groupUsers")
    public ResponseEntity<List<GroupUser>> getAllGroupUser(){
        List<GroupUser> groupUserList = groupUserService.listAll();
        return new ResponseEntity<List<GroupUser>>(groupUserList,HttpStatus.OK);
    }

    //@GetMapping("/groupUsers/summary")
    //public ResponseEntity<List<DTOGroupUserSummary>> getGroupUsersSummary() {
    //    List<DTOGroupUserSummary> dtoGroupUserSummaryList = groupUserService.listGroupUserSummary();
    //    return new ResponseEntity<List<DTOGroupUserSummary>>(dtoGroupUserSummaryList, HttpStatus.OK);
    //}

}
