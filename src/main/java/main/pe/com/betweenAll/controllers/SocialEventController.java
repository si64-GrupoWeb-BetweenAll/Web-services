package main.pe.com.betweenAll.controllers;

import main.pe.com.betweenAll.dtos.DTOSocialEventsAvailableSummary;
import main.pe.com.betweenAll.dtos.DTOUserCategorySummary;
import main.pe.com.betweenAll.entities.DateSocialEvent;
import main.pe.com.betweenAll.entities.SocialEvent;
import main.pe.com.betweenAll.repositories.DateSocialEventRepository;
import main.pe.com.betweenAll.repositories.SocialEventRepository;
import main.pe.com.betweenAll.services.DateSocialEventService;
import main.pe.com.betweenAll.services.SocialEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class SocialEventController {

    @Autowired
    SocialEventService socialEventService;
    @Autowired
    SocialEventRepository socialEventRepository;
    @Autowired
    DateSocialEventService dateSocialEventService;
    @Autowired
    DateSocialEventRepository dateSocialEventRepository;

    @GetMapping("/socialEvents")
    public ResponseEntity<List<SocialEvent>> getAllSocialEvent() {
        List<SocialEvent> social_events = socialEventService.listAll();
        return new ResponseEntity<List<SocialEvent>>(social_events, HttpStatus.OK);
    }

    @GetMapping("/socialEvents/created/{id}")
    public ResponseEntity<List<SocialEvent>> getAllSocialEventCreatedByUser(@PathVariable("id") Long id) {
        List<SocialEvent> social_events = socialEventService.listByCreated(id);
        return new ResponseEntity<List<SocialEvent>>(social_events, HttpStatus.OK);

    }

    @GetMapping("/socialEvents/{id}")
    public ResponseEntity<SocialEvent> getAllSocialEventsById(@PathVariable("id") Long id) {
        SocialEvent social_event = socialEventService.listById(id);
        return new ResponseEntity<SocialEvent>(social_event, HttpStatus.OK);
    }
    @GetMapping("/socialEvents/End")
    public ResponseEntity<SocialEvent> socialEventEnd() {
        SocialEvent social_event = socialEventService.socialEventEnd();
        return new ResponseEntity<SocialEvent>(social_event, HttpStatus.OK);
    }
    @PostMapping("/socialEvents/{idCategory}/{idUser}")
    public ResponseEntity<SocialEvent> createSocialEvent(@RequestBody SocialEvent social_event,
                                                         @PathVariable("idCategory") Long idCategory,
                                                         @PathVariable("idUser") Long idUser){
        SocialEvent newSocialEvent = socialEventService.save(social_event,idCategory,idUser);
        return new ResponseEntity<SocialEvent>(newSocialEvent, HttpStatus.CREATED);
    }


    @DeleteMapping("/socialEvents/{id}")
    public ResponseEntity<HttpStatus> deleteSocialEvent(@PathVariable("id") Long id) {
        socialEventService.delete(id, true);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/socialEvents/{idCategory}/{idUser}/{id}")
    public ResponseEntity<SocialEvent> updateSocialEvent(@RequestBody SocialEvent social_event,
                                                         @PathVariable("idCategory") Long idCategory,
                                                         @PathVariable("idUser") Long idUser,
                                                         @PathVariable("id") Long id) {
        SocialEvent foundSocialEvent=socialEventService.listById(id);

        if(social_event.getName()!=null){
            foundSocialEvent.setName(social_event.getName());
        }
        if(social_event.getImage()!=null){
            foundSocialEvent.setImage(social_event.getImage());
        }
        if(social_event.getLocation()!=null){
            foundSocialEvent.setLocation(social_event.getLocation());
        }
        if(social_event.getDescription()!=null){
            foundSocialEvent.setDescription(social_event.getDescription());
        }
        if(social_event.getUser()!=null){
            foundSocialEvent.setUser(social_event.getUser());
        }
        if(social_event.getCategory()!=null){
            foundSocialEvent.setCategory(social_event.getCategory());
        }

        SocialEvent updateSocialEvent = socialEventService.save(foundSocialEvent,idCategory,idUser);
        return new ResponseEntity<SocialEvent>(updateSocialEvent, HttpStatus.OK);
    }

    @GetMapping("/socialEventsAvailable/summary")
    public List<DTOSocialEventsAvailableSummary> listSocialEventsAvailableSummary() {
        List<DTOSocialEventsAvailableSummary> socialEventsAvailableSummaries = socialEventService.listSocialEventsAvailableSummary();
        return socialEventsAvailableSummaries;
    }

}
