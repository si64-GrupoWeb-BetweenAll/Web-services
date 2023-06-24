package main.pe.com.betweenAll.servicesimpl;

import main.pe.com.betweenAll.dtos.DTOSocialEventsAvailableSummary;
import main.pe.com.betweenAll.entities.*;

import main.pe.com.betweenAll.dtos.DTOSocialEventSummary;
import main.pe.com.betweenAll.dtos.DTOUserCategorySummary;

import main.pe.com.betweenAll.exceptions.IncompleteDataException;
import main.pe.com.betweenAll.repositories.*;
import main.pe.com.betweenAll.services.SocialEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.module.ResolutionException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SocialEventServiceImpl implements SocialEventService {

    @Autowired
    SocialEventRepository socialEventRepository;
    @Autowired
    DateSocialEventRepository dateSocialEventRepository;
    @Autowired
    ZoneEventRepository zoneEventRepository;
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    UserRepository userRepository;

    @Transactional
    public List<SocialEvent> listAll() {
        List<SocialEvent> socialEvents;
        socialEvents= socialEventRepository.findAll();

        for(SocialEvent s: socialEvents){
            s.getUser().setSocialEventList(null);
            s.getUser().setGroupUserList(null);
            s.getUser().setPurchaseList(null);
            s.getUser().setUserCategoryList(null);
            s.getUser().setAuthorityList(null);
            s.getUser().setCardList(null);
            s.getCategory().setGroupList(null);
            s.getCategory().setSocialEventList(null);
            s.getCategory().setUserCategoryList(null);
            s.setDateSocialEventList(null);
        }
         return socialEvents;

    }

    public SocialEvent listById(Long id) {
        SocialEvent socialEvent;
        socialEvent=socialEventRepository.findById(id).orElseThrow(()->new ResolutionException("Not found an Social Event with id="+id));
        ;
        return socialEvent;
    }

    @Transactional
    public SocialEvent save(SocialEvent socialEvent) {

        if (socialEvent.getDescription() == null || socialEvent.getDescription().isEmpty()) {
            throw new IncompleteDataException("Social Event Description cannot be null or empty");
        }

        if (socialEvent.getImage() == null || socialEvent.getImage().isEmpty()) {
            throw new IncompleteDataException("Social Event Image cannot be null or empty");
        }

        if (socialEvent.getName() == null || socialEvent.getName().isEmpty()) {
            throw new IncompleteDataException("Social Event Name cannot be null or empty");
        }

        if (socialEvent.getLocation() == null || socialEvent.getLocation().isEmpty()) {
            throw new IncompleteDataException("Social Event Location cannot be null or empty");
        }

        //REVISAR
        /*if (socialEvent.getDateSocialEventList() == null || socialEvent.getDateSocialEventList().isEmpty()) {
            throw new IncompleteDataException("Social Event Date cannot be null or empty");
        }*/

        SocialEvent newSocialEvent = socialEventRepository.save(new SocialEvent(socialEvent.getName(), socialEvent.getImage(), socialEvent.getLocation(), socialEvent.getDescription(), socialEvent.getUser(), socialEvent.getCategory()));
        return newSocialEvent;
    }

    @Transactional
    public void delete(Long id, boolean forced) {
        SocialEvent SocialEvent = socialEventRepository.findById(id).get();
        if (forced){
            List<DateSocialEvent> dateSocialEvents = dateSocialEventRepository.findBySocialEvent_Id(id);
            for(DateSocialEvent dse: dateSocialEvents){
                dateSocialEventRepository.delete(dse);
            }
        }
        socialEventRepository.delete(SocialEvent);
    }
    @Transactional
    public List<DTOSocialEventSummary> listSocialEventSummary () {

        List<SocialEvent>socialEventList=socialEventRepository.findAll();
        List<DTOSocialEventSummary> dtoSocialEventSummaryList = new ArrayList<>();

        for(SocialEvent sE: socialEventList) {
            String informationSocialEvent = sE.getName() + " - " + sE.getCategory();
            Integer countDateSocialEvent = (int) sE.getDateSocialEventList().stream().count();
            DTOSocialEventSummary dtoSocialEventSummary= new DTOSocialEventSummary(informationSocialEvent, countDateSocialEvent);
            dtoSocialEventSummaryList.add(dtoSocialEventSummary);
        }
        return dtoSocialEventSummaryList;
    }

    /*@Transactional
    public List<DTOSocialEventsAvailableSummary> listSocialEventsAvailableSummary(){
        return socialEventRepository.listSocialEventAvailableSummary();
    }*/

    @Transactional
    public List<DTOSocialEventsAvailableSummary> listSocialEventsAvailableSummary () {
        List<String[]> socialEventList =socialEventRepository.listSocialEventAvailableSummary();
        List<DTOSocialEventsAvailableSummary> dtoSocialEventsAvailableSummaryList = new ArrayList<>();

        for(String[] s: socialEventList){
            DTOSocialEventsAvailableSummary dto = new DTOSocialEventsAvailableSummary();
            dto.setNameSocialEvent(s[0]);
            dto.setAmountTotalTickets(Integer.parseInt(s[1]));
            dto.setAmountBuyTickets(Integer.parseInt(s[2]));
            dto.setAmountAvailableTickets(Integer.parseInt(s[3]));
            dtoSocialEventsAvailableSummaryList.add(dto);
        }
        return dtoSocialEventsAvailableSummaryList;
    }

    /*
    @Transactional
    public List<DTOSocialEventsAvailableSummary> listSocialEventsAvailableSummary(){
        List<SocialEvent> socialEventList = socialEventRepository.findAll();
        List<DateSocialEvent> dateSocialEventList = dateSocialEventRepository.findAll();
        List<ZoneEvent> zoneEventList = zoneEventRepository.findAll();

        List<DTOSocialEventsAvailableSummary> dtoSocialEventsAvailableSummaryList = new ArrayList<>();
        Integer amountTickets=0;
        for(SocialEvent s: socialEventList){
            if(amountTickets==0){
                for (DateSocialEvent d:dateSocialEventList){
                    for (ZoneEvent z: zoneEventList){
                        amountTickets+=(int)z.getCapacity();
                    }
                }
                DTOSocialEventsAvailableSummary dtoSocialEventsAvailableSummary = new DTOSocialEventsAvailableSummary(
                        s.getName(),amountTickets,1,1);
                amountTickets=0;
                dtoSocialEventsAvailableSummaryList.add(dtoSocialEventsAvailableSummary);
            }

        }
        return dtoSocialEventsAvailableSummaryList;
    }*/

    public List<SocialEvent> listByCreated(Long id){
        List<SocialEvent> socialEventList = socialEventRepository.findSocialEventByUser(id);
        for(SocialEvent s: socialEventList){
            s.getUser().setSocialEventList(null);
            s.getUser().setGroupUserList(null);
            s.getUser().setPurchaseList(null);
            s.getUser().setUserCategoryList(null);
            s.getUser().setAuthorityList(null);
            s.getUser().setCardList(null);
            s.getCategory().setGroupList(null);
            s.getCategory().setSocialEventList(null);
            s.getCategory().setUserCategoryList(null);
            s.setDateSocialEventList(null);
        }
        return socialEventList;

    }
}
