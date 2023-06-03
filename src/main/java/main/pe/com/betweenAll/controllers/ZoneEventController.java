package main.pe.com.betweenAll.controllers;

import main.pe.com.betweenAll.entities.Card;
import main.pe.com.betweenAll.entities.ZoneEvent;
import main.pe.com.betweenAll.services.ZoneEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ZoneEventController {
    @Autowired
    ZoneEventService zoneEventService;

    @GetMapping("/zone_events")
    public ResponseEntity<List<ZoneEvent>> getAllZoneEvents() {
        List<ZoneEvent> zoneEvents = zoneEventService.listAll();
        return new ResponseEntity<List<ZoneEvent>>(zoneEvents, HttpStatus.OK);
    }

    @GetMapping("/zone_events/{id}")
    public ResponseEntity<ZoneEvent> getAllZoneEventById(@PathVariable("id") Long id) {
        ZoneEvent zoneEvent = zoneEventService.listById(id);
        return new ResponseEntity<ZoneEvent>(zoneEvent, HttpStatus.OK);

    }

    @PostMapping("/zone_events")
    public ResponseEntity<ZoneEvent> createZoneEvent (@RequestBody ZoneEvent zoneEvent) {
        ZoneEvent newZoneEvent = zoneEventService.save(zoneEvent);
        return new ResponseEntity<ZoneEvent>(newZoneEvent, HttpStatus.CREATED);
    }

    @DeleteMapping("/zone_events/{id}")
    public ResponseEntity<HttpStatus> deleteZoneEvent (@PathVariable("id") Long id) {
        zoneEventService.delete(id, true);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
