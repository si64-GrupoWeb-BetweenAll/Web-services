package main.pe.com.betweenAll.controllers;

import main.pe.com.betweenAll.dtos.DTOGroupParticipantsSummary;
import main.pe.com.betweenAll.dtos.DTOTicketSummary;
import main.pe.com.betweenAll.entities.Ticket;
import main.pe.com.betweenAll.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @GetMapping("/tickets")
    public ResponseEntity<List<Ticket>> getAllTickets() {
        List<Ticket> tickets = ticketService.listAll();
        return new ResponseEntity<List<Ticket>>(tickets, HttpStatus.OK);

    }

    @GetMapping("/tickets/{id}")
    public ResponseEntity<Ticket> getAllTicketsById(@PathVariable("id") Long id) {
        Ticket ticket = ticketService.listById(id);
        return new ResponseEntity<Ticket>(ticket, HttpStatus.OK);

    }

    @PostMapping("/tickets")
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket) {
        Ticket newTicket = ticketService.save(ticket);
        return new ResponseEntity<Ticket>(newTicket, HttpStatus.CREATED);
    }

    @DeleteMapping("/tickets/{id}")
    public ResponseEntity<HttpStatus> deleteTicket(@PathVariable("id") Long id) {
        ticketService.delete(id, true);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/tickets/{id}")
    public ResponseEntity<Ticket> updateTicket(@RequestBody Ticket ticket, @PathVariable("id") Long id) {
        Ticket foundTicket=ticketService.listById(id);

        if(ticket.getPurchase()!=null){
            foundTicket.setPurchase(ticket.getPurchase());
        }
        if(ticket.getZoneEvent()!=null){
            foundTicket.setZoneEvent(ticket.getZoneEvent());
        }

        Ticket updateTicket = ticketService.save(foundTicket);
        return new ResponseEntity<Ticket>(updateTicket, HttpStatus.OK);
    }

    @GetMapping("/tickets/summary/{id}")
    public ResponseEntity<List<DTOTicketSummary>>getTicketByUserSummary(@PathVariable("id") Long id){
        List<DTOTicketSummary>dtoTicketSummaryList=ticketService.listTicketByUserSummary(id);
        return new ResponseEntity<List<DTOTicketSummary>>(dtoTicketSummaryList, HttpStatus.OK);
    }
}
