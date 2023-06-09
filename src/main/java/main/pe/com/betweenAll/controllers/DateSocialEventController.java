package main.pe.com.betweenAll.controllers;

import main.pe.com.betweenAll.dtos.DTOZoneAvailableSummary;
import main.pe.com.betweenAll.entities.DateSocialEvent;
import main.pe.com.betweenAll.entities.SocialEvent;
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

    @PostMapping("/dateSocialEvents/{idSocialEvent}")
    public ResponseEntity<DateSocialEvent> createDateSocialEvent(@RequestBody DateSocialEvent dateSocialEvent,
                                                              @PathVariable("idSocialEvent") Long idSocialEvent){
        DateSocialEvent saveDateSocialEvent =dateSocialEventService.save(dateSocialEvent,idSocialEvent);
        return new ResponseEntity<DateSocialEvent>(saveDateSocialEvent, HttpStatus.CREATED);
    }
    @DeleteMapping("/dateSocialEvents/{id}")
    public ResponseEntity<HttpStatus> deleteDateSocialEvent(@PathVariable("id") Long id){
        dateSocialEventService.delete(id,true);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/dateSocialEvents")
    public ResponseEntity<List<DateSocialEvent>> getAllUserCategory(){
        List<DateSocialEvent> dateSocialEventList = dateSocialEventService.listAll();
        return new ResponseEntity<List<DateSocialEvent>>(dateSocialEventList, HttpStatus.OK);
    }
    @GetMapping("/dateSocialEvents/End")
    public ResponseEntity<DateSocialEvent> dateEventEnd() {
        DateSocialEvent dateSocialEvent = dateSocialEventService.dateEventEnd();
        return new ResponseEntity<DateSocialEvent>(dateSocialEvent, HttpStatus.OK);
    }
    @GetMapping("/dateSocialEvents/summary")
    public ResponseEntity<List<DTOZoneAvailableSummary>> getAssistedTicketsSummary() {
        List<DTOZoneAvailableSummary> dtoZoneAvailableSummaryList = dateSocialEventService.listZoneAvailableSummary();
        return new ResponseEntity<List<DTOZoneAvailableSummary>>(dtoZoneAvailableSummaryList, HttpStatus.OK);
    }

    @GetMapping("/dateSocialEvents/Event/{idEvent}")
    public ResponseEntity<List<DateSocialEvent>> listBySocialEvent(@PathVariable("idEvent") Long idEvent) {
        List<DateSocialEvent> dateSocialEventList = dateSocialEventService.listBySocialEvent(idEvent);
        return new ResponseEntity<List<DateSocialEvent>>(dateSocialEventList, HttpStatus.OK);
    }

}
