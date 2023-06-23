package main.pe.com.betweenAll.servicesimpl;

import main.pe.com.betweenAll.dtos.DTOAssistedTicketsSummary;
import main.pe.com.betweenAll.dtos.DTOSocialEventSummary;
import main.pe.com.betweenAll.entities.Purchase;
import main.pe.com.betweenAll.entities.SocialEvent;
import main.pe.com.betweenAll.entities.Ticket;
import main.pe.com.betweenAll.entities.User;
import main.pe.com.betweenAll.exceptions.IncompleteDataException;
import main.pe.com.betweenAll.repositories.PurchaseRepository;
import main.pe.com.betweenAll.repositories.TicketRepository;
import main.pe.com.betweenAll.repositories.UserRepository;
import main.pe.com.betweenAll.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.module.ResolutionException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketRepository ticketRepository;


    @Transactional
    public List<Ticket> listAll() {
        List<Ticket> tickets;
        tickets= ticketRepository.findAll();
        for(Ticket s: tickets){
            s.getPurchase().setUser(null);
            s.getPurchase().setTicketList(null);
            s.getPurchase().setCard(null);
            s.getZoneEvent().setTicketList(null);
            s.getZoneEvent().setDateSocialEvent(null);
        }
        return tickets;
    }
    @Transactional
    public Ticket listById(Long id) {
        Ticket ticket;
        ticket=ticketRepository.findById(id).orElseThrow(()->new ResolutionException("Not found an Ticket with id="+id));

        return ticket;
    }

    @Transactional
    public Ticket save(Ticket ticket) {
        Ticket newTicket = ticketRepository.save(new Ticket(ticket.getPurchase(), ticket.getZoneEvent()));
        return newTicket;
    }

    @Transactional
    public void delete(Long id, boolean forced) {
        Ticket ticket = ticketRepository.findById(id).get();
        ticketRepository.delete(ticket);
    }

}
