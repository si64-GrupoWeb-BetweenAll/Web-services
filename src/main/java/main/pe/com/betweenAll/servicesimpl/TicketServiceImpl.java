package main.pe.com.betweenAll.servicesimpl;

//import main.pe.com.betweenAll.dtos.DTOAssistedTicketsSummary;
import main.pe.com.betweenAll.dtos.DTOGroupParticipantsSummary;
import main.pe.com.betweenAll.dtos.DTOSocialEventSummary;
import main.pe.com.betweenAll.dtos.DTOTicketSummary;
import main.pe.com.betweenAll.entities.*;

import main.pe.com.betweenAll.repositories.CategoryRepository;
import main.pe.com.betweenAll.repositories.PurchaseRepository;
import main.pe.com.betweenAll.entities.Ticket;
import main.pe.com.betweenAll.repositories.TicketRepository;
import main.pe.com.betweenAll.repositories.ZoneEventRepository;
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
    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    ZoneEventRepository zoneEventRepository;

    @Transactional
    public List<Ticket> listAll() {
        List<Ticket> tickets;
        tickets= ticketRepository.findAll();
        for(Ticket s: tickets){
            s.getPurchase().setTicketList(null);
            s.getPurchase().setCard(null);
            s.getZoneEvent().setTicketList(null);
            s.getZoneEvent().setDateSocialEvent(null);
            s.getPurchase().getUser().setGroupUserList(null);
            s.getPurchase().getUser().setGroupList(null);
            s.getPurchase().setUser(null);
            for(Authority a: s.getPurchase().getUser().getAuthorityList()){
                a.setUsers(null);
            }
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
    public Ticket save(Ticket ticket, Long idPurchase, Long idZoneEvent) {
        Purchase purchase = purchaseRepository.findById(idPurchase).get();
        ZoneEvent zoneEvent = zoneEventRepository.findById(idZoneEvent).get();

        ticket.setPurchase(purchase);
        ticket.setZoneEvent(zoneEvent);
        Ticket newTicket = ticketRepository.save(ticket);

        //Ticket newTicket = ticketRepository.save(new Ticket(ticket.getPurchase(), ticket.getZoneEvent()));
        return newTicket;
    }

    @Transactional
    public void delete(Long id, boolean forced) {
        Ticket ticket = ticketRepository.findById(id).get();
        ticketRepository.delete(ticket);
    }

    //Implementacion de Resumen de Ticket
    @Transactional
    public List<DTOTicketSummary> listTicketByUserSummary(Long id){
        List<Ticket>ticketList=ticketRepository.findAll();

        List<DTOTicketSummary>dtoTicketSummaryList=new ArrayList<>();
        for (Ticket sE:ticketList){
            if (sE.getPurchase().getUser().getId()==id){
                Long infIdTicket=sE.getId();
                String infNameTicket=sE.getPurchase().getUser().getName()+" "+sE.getPurchase().getUser().getLastname();
                String infNameSocialEvent=sE.getZoneEvent().getDateSocialEvent().getSocialEvent().getName();
                String infNameZoneEvent=sE.getZoneEvent().getName();
                String infDateSocialEvent=sE.getZoneEvent().getDateSocialEvent().getDate().toString();
                String infLocationSocialEvent=sE.getZoneEvent().getDateSocialEvent().getSocialEvent().getLocation();
                String infEmailUser=sE.getPurchase().getUser().getEmail();
                Double infAmountPurchase=sE.getZoneEvent().getPrice();
                DTOTicketSummary dtoTicketSummary=new DTOTicketSummary(infIdTicket,infNameTicket,infNameSocialEvent,
                        infNameZoneEvent,infDateSocialEvent,infLocationSocialEvent,infEmailUser,infAmountPurchase);
                dtoTicketSummaryList.add(dtoTicketSummary);
            }
        }
        return dtoTicketSummaryList;
    }
}
