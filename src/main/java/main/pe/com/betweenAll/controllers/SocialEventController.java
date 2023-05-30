package main.pe.com.betweenAll.controllers;

import main.pe.com.betweenAll.entities.SocialEvent;
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

    @GetMapping("/social_events")
    public ResponseEntity<List<SocialEvent>> getAllSocialEvent() {
        List<SocialEvent> social_events = socialEventService.listAll();
        return new ResponseEntity<List<SocialEvent>>(social_events, HttpStatus.OK);

    }

    @GetMapping("/social_events/{id}")
    public ResponseEntity<SocialEvent> getAllSocialEventsById(@PathVariable("id") Long id) {
        SocialEvent social_event = socialEventService.listById(id);
        return new ResponseEntity<SocialEvent>(social_event, HttpStatus.OK);

    }

    @PostMapping("/social_events")
    public ResponseEntity<SocialEvent> createSocialEvent(@RequestBody SocialEvent social_event) {
        SocialEvent newSocialEvent = socialEventService.save(social_event);
        return new ResponseEntity<SocialEvent>(newSocialEvent, HttpStatus.CREATED);
    }

    @DeleteMapping("/social_events/{id}")
    public ResponseEntity<HttpStatus> deleteSocialEvent(@PathVariable("id") Long id) {
        socialEventService.delete(id, true);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/social_events/{id}")
    public ResponseEntity<SocialEvent> updateSocialEvent(@RequestBody SocialEvent social_event, @PathVariable("id") Long id) {
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

        SocialEvent updateSocialEvent = socialEventService.save(foundSocialEvent);
        return new ResponseEntity<SocialEvent>(updateSocialEvent, HttpStatus.OK);
    }

}
