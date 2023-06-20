package main.pe.com.betweenAll.servicesimpl;

import main.pe.com.betweenAll.entities.Card;
import main.pe.com.betweenAll.entities.SocialEvent;
import main.pe.com.betweenAll.entities.DateSocialEvent;
import main.pe.com.betweenAll.repositories.SocialEventRepository;
import main.pe.com.betweenAll.repositories.DateSocialEventRepository;
import main.pe.com.betweenAll.services.SocialEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SocialEventServiceImpl implements SocialEventService {

    @Autowired
    SocialEventRepository socialEventRepository;
    DateSocialEventRepository dateSocialEventRepository;
    @Transactional
    public List<SocialEvent> listAll() {
        List<SocialEvent> socialEvents;
        socialEvents= socialEventRepository.findAll();
        for(SocialEvent s: socialEvents){
            s.getUser().setGroupUserList(null);
            s.getUser().setUserCategoryList(null);
            s.getUser().setSocialEventList(null);
            s.getUser().setCardList(null);
            s.getCategory().setSocialEventList(null);
            s.getCategory().setGroupList(null);
            s.setDateSocialEventList(null);
        }
        return socialEvents;
    }

    public SocialEvent listById(Long id) {
        SocialEvent socialEvent;
        socialEvent=socialEventRepository.findById(id).get();
        return socialEvent;
    }

    @Transactional
    public SocialEvent save(SocialEvent socialEvent) {
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
}
