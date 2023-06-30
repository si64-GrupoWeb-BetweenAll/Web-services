package main.pe.com.betweenAll.servicesimpl;


import main.pe.com.betweenAll.entities.DateSocialEvent;
import main.pe.com.betweenAll.entities.Ticket;
import main.pe.com.betweenAll.entities.ZoneEvent;
import main.pe.com.betweenAll.repositories.DateSocialEventRepository;
import main.pe.com.betweenAll.repositories.TicketRepository;
import main.pe.com.betweenAll.repositories.ZoneEventRepository;
import main.pe.com.betweenAll.services.ZoneEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ZoneEventServiceImpl implements ZoneEventService {
    @Autowired
    ZoneEventRepository zoneEventRepository;
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    DateSocialEventRepository dateSocialEventRepository;

    public List<ZoneEvent> listAll() {
        List<ZoneEvent> zoneEvents;
        zoneEvents= zoneEventRepository.findAll();
        for(ZoneEvent s: zoneEvents){
            s.getDateSocialEvent().setSocialEvent(null);
            s.getDateSocialEvent().setZoneEventList(null);
            s.setTicketList(null);
        }
        return zoneEvents;
    }

    public ZoneEvent listById(Long id) {
        ZoneEvent zoneEvent;
        zoneEvent=zoneEventRepository.findById(id).get();
        zoneEvent.getDateSocialEvent().setSocialEvent(null);
        zoneEvent.getDateSocialEvent().setZoneEventList(null);
        zoneEvent.setTicketList(null);
        return zoneEvent;
    }

    public List<ZoneEvent> listByName(String name) {
        List<ZoneEvent> zoneEvents;
        zoneEvents=zoneEventRepository.findByNameContaining(name);
        for(ZoneEvent c: zoneEvents){
            c.setTicketList(null);
        }
        return zoneEvents;
    }
    @Transactional
    public ZoneEvent save(ZoneEvent zoneEvent, Long idDateSocialEvent) {
        DateSocialEvent dateSocialEvent=dateSocialEventRepository.findById(idDateSocialEvent).get();
        zoneEvent.setDateSocialEvent(dateSocialEvent);
        ZoneEvent newSocialEvent = zoneEventRepository.save(zoneEvent);

        newSocialEvent.getDateSocialEvent().getSocialEvent().setDateSocialEventList(null);
        newSocialEvent.getDateSocialEvent().getSocialEvent().getUser().setSocialEventList(null);
        newSocialEvent.getDateSocialEvent().getSocialEvent().getUser().setGroupList(null);
        newSocialEvent.getDateSocialEvent().getSocialEvent().getUser().setGroupUserList(null);
        newSocialEvent.getDateSocialEvent().getSocialEvent().getUser().setUserCategoryList(null);
        newSocialEvent.getDateSocialEvent().getSocialEvent().getUser().setPurchaseList(null);
        newSocialEvent.getDateSocialEvent().getSocialEvent().getUser().setAuthorityList(null);
        newSocialEvent.getDateSocialEvent().getSocialEvent().getUser().setCardList(null);
        newSocialEvent.getDateSocialEvent().getSocialEvent().setDateSocialEventList(null);
        newSocialEvent.getDateSocialEvent().getSocialEvent().getCategory().setGroupList(null);
        newSocialEvent.getDateSocialEvent().getSocialEvent().getCategory().setSocialEventList(null);
        newSocialEvent.getDateSocialEvent().getSocialEvent().getCategory().setUserCategoryList(null);

        newSocialEvent.getDateSocialEvent().setZoneEventList(null);
        newSocialEvent.setTicketList(null);
        return newSocialEvent;
    }

    @Transactional
    public void delete(Long id, boolean forced) {
        ZoneEvent zoneEvent = zoneEventRepository.findById(id).get();
        if (forced){
            List<Ticket> tickets = ticketRepository.findByZoneEvent_Id(id);
            for(Ticket dse: tickets){
                ticketRepository.delete(dse);
            }
        }
        zoneEventRepository.delete(zoneEvent);
    }
    public List<ZoneEvent> listByDateEvent(Long id){
        List<ZoneEvent> zoneEventList = zoneEventRepository.findZoneEventByDateEvent(id);
        for(ZoneEvent s: zoneEventList){
            s.getDateSocialEvent().setSocialEvent(null);
            s.getDateSocialEvent().setZoneEventList(null);
            s.setTicketList(null);
        }
        return zoneEventList;
    }
}
