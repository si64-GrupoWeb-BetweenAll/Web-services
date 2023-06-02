package main.pe.com.betweenAll.controllers;

import main.pe.com.betweenAll.entities.DateSocialEvent;
import main.pe.com.betweenAll.services.DateSocialEventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api")
public class DateSocialEventController {
    @Autowired
    DateSocialEventService dateSocialEventService;

    @PostMapping("/dateSocialEvents")
    public ResponseEntity<DateSocialEvent> createUserCategory(@RequestBody DateSocialEvent dateSocialEvent){
        DateSocialEvent saveDateSocialEvent =dateSocialEventService.save(dateSocialEvent);
        return new ResponseEntity<DateSocialEvent>(saveDateSocialEvent, HttpStatus.CREATED);
    }
    @DeleteMapping("/dateSocialEvents/{id}")
    public ResponseEntity<HttpStatus> deleteUserCategory(@PathVariable("id") Long id){
        dateSocialEventService.delete(id,true);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/dateSocialEvents")
    public ResponseEntity<List<DateSocialEvent>> getAllUserCategory(){
        List<DateSocialEvent> dateSocialEventList = dateSocialEventService.listAll();
        return new ResponseEntity<List<DateSocialEvent>>(dateSocialEventList, HttpStatus.OK);
    }
}
