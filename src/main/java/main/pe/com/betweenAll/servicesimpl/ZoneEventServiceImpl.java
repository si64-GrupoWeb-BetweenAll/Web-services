package main.pe.com.betweenAll.servicesimpl;


import main.pe.com.betweenAll.entities.Ticket;
import main.pe.com.betweenAll.entities.ZoneEvent;
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

    public List<ZoneEvent> listAll() {
        List<ZoneEvent> zoneEvents;
        zoneEvents= zoneEventRepository.findAll();
        return zoneEvents;
    }

    public ZoneEvent listById(Long id) {
        ZoneEvent zoneEvent;
        zoneEvent=zoneEventRepository.findById(id).get();
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
    public ZoneEvent save(ZoneEvent zoneEvent) {
        ZoneEvent newSocialEvent =
                zoneEventRepository.save(
                        new ZoneEvent(zoneEvent.getName(), zoneEvent.getPrice(), zoneEvent.getCapacity(), zoneEvent.getDateSocialEvent()));
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

}
