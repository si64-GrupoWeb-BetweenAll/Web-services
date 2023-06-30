package main.pe.com.betweenAll.servicesimpl;

import main.pe.com.betweenAll.dtos.DTOSocialEventsAvailableSummary;
import main.pe.com.betweenAll.entities.*;

import main.pe.com.betweenAll.exceptions.IncompleteDataException;
import main.pe.com.betweenAll.repositories.*;
import main.pe.com.betweenAll.services.SocialEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.module.ResolutionException;
import java.util.ArrayList;
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
    @Autowired
    CategoryRepository categoryRepository;

    @Transactional
    public List<SocialEvent> listAll() {
        List<SocialEvent> socialEvents;
        socialEvents= socialEventRepository.findAll();

        for(SocialEvent s: socialEvents){
            s.getUser().setSocialEventList(null);
            s.getUser().setGroupUserList(null);
            s.getUser().setPurchaseList(null);
            s.getUser().setUserCategoryList(null);
            s.getUser().setCardList(null);
            s.getUser().setGroupList(null);
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
        socialEvent.getUser().setSocialEventList(null);
        socialEvent.getUser().setGroupList(null);
        socialEvent.getUser().setGroupUserList(null);
        socialEvent.getUser().setPurchaseList(null);
        socialEvent.getUser().setUserCategoryList(null);
        socialEvent.getUser().setAuthorityList(null);
        socialEvent.getUser().setCardList(null);
        socialEvent.getCategory().setGroupList(null);
        socialEvent.getCategory().setSocialEventList(null);
        socialEvent.getCategory().setUserCategoryList(null);
        socialEvent.setDateSocialEventList(null);
        return socialEvent;
    }

    @Transactional
    public SocialEvent save(SocialEvent socialEvent,Long idCategory,Long idUser) {

        if (socialEvent.getDescription() == null || socialEvent.getDescription().isEmpty()) {throw new IncompleteDataException("Social Event Description cannot be null or empty");}
        if (socialEvent.getImage() == null || socialEvent.getImage().isEmpty()) {throw new IncompleteDataException("Social Event Image cannot be null or empty");}
        if (socialEvent.getName() == null || socialEvent.getName().isEmpty()) {throw new IncompleteDataException("Social Event Name cannot be null or empty");}
        if (socialEvent.getLocation() == null || socialEvent.getLocation().isEmpty()) {throw new IncompleteDataException("Social Event Location cannot be null or empty");}



        Category category=categoryRepository.findById(idCategory).get();
        User user=userRepository.findById(idUser).get();
        socialEvent.setCategory(category);
        socialEvent.setUser(user);
        SocialEvent newSocialEvent = socialEventRepository.save(socialEvent);

        newSocialEvent.getUser().setSocialEventList(null);
        newSocialEvent.getUser().setGroupUserList(null);
        newSocialEvent.getUser().setPurchaseList(null);
        newSocialEvent.getUser().setUserCategoryList(null);
        newSocialEvent.getUser().setCardList(null);
        newSocialEvent.getUser().setGroupList(null);
        newSocialEvent.getCategory().setGroupList(null);
        newSocialEvent.getCategory().setSocialEventList(null);
        newSocialEvent.getCategory().setUserCategoryList(null);
        newSocialEvent.setDateSocialEventList(null);

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
            s.getUser().setGroupList(null);
        }
        return socialEventList;
    }

    public SocialEvent socialEventEnd() {
        SocialEvent socialEvent;
        socialEvent=socialEventRepository.socialEventEnd();

        socialEvent.getUser().setSocialEventList(null);
        socialEvent.getUser().setGroupList(null);
        socialEvent.getUser().setGroupUserList(null);
        socialEvent.getUser().setPurchaseList(null);
        socialEvent.getUser().setUserCategoryList(null);
        socialEvent.getUser().setAuthorityList(null);
        socialEvent.getUser().setCardList(null);
        socialEvent.getCategory().setGroupList(null);
        socialEvent.getCategory().setSocialEventList(null);
        socialEvent.getCategory().setUserCategoryList(null);
        socialEvent.setDateSocialEventList(null);

        return socialEvent;
    }
}
