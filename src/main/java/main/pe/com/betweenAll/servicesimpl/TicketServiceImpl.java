package main.pe.com.betweenAll.servicesimpl;

import main.pe.com.betweenAll.entities.Purchase;
import main.pe.com.betweenAll.entities.Ticket;
import main.pe.com.betweenAll.repositories.PurchaseRepository;
import main.pe.com.betweenAll.repositories.TicketRepository;
import main.pe.com.betweenAll.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketRepository ticketRepository;

    public List<Ticket> listAll() {
        List<Ticket> tickets;
        tickets= ticketRepository.findAll();
        return tickets;
    }

    public Ticket listById(Long id) {
        Ticket ticket;
        ticket=ticketRepository.findById(id).get();
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
