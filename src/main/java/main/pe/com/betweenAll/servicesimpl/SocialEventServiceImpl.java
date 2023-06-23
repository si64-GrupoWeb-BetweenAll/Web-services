package main.pe.com.betweenAll.servicesimpl;

import main.pe.com.betweenAll.dtos.DTOSocialEventSummary;
import main.pe.com.betweenAll.dtos.DTOUserCategorySummary;
import main.pe.com.betweenAll.entities.SocialEvent;
import main.pe.com.betweenAll.entities.DateSocialEvent;
import main.pe.com.betweenAll.entities.UserCategory;
import main.pe.com.betweenAll.exceptions.IncompleteDataException;
import main.pe.com.betweenAll.repositories.SocialEventRepository;
import main.pe.com.betweenAll.repositories.DateSocialEventRepository;
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


    @Transactional
    public List<SocialEvent> listAll() {
        List<SocialEvent> socialEvents;
        socialEvents= socialEventRepository.findAll();
        for()
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
        if (socialEvent.getDateSocialEventList() == null || socialEvent.getDateSocialEventList().isEmpty()) {
            throw new IncompleteDataException("Social Event Date cannot be null or empty");
        }

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
}
