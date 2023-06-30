package main.pe.com.betweenAll.servicesimpl;

import main.pe.com.betweenAll.dtos.DTOZoneAvailableSummary;
import main.pe.com.betweenAll.entities.*;
import main.pe.com.betweenAll.repositories.*;
import main.pe.com.betweenAll.services.DateSocialEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class DateSocialEventServiceImpl implements DateSocialEventService {

    @Autowired
    DateSocialEventRepository dateSocialEventRepository;
    @Autowired
    SocialEventRepository socialEventRepository;
    @Autowired
    ZoneEventRepository zoneEventRepository;

    @Transactional
    public DateSocialEvent save(DateSocialEvent dateSocialEvent,Long idSocialEvent){
        SocialEvent socialEvent = socialEventRepository.findById(idSocialEvent).get();
        dateSocialEvent.setSocialEvent(socialEvent);
        dateSocialEvent.getSocialEvent().setUser(null);
        dateSocialEvent.getSocialEvent().setCategory(null);
        dateSocialEvent.getSocialEvent().setDateSocialEventList(null);
        dateSocialEvent.setZoneEventList(null);
        return dateSocialEventRepository.save(dateSocialEvent);
    }
    @Transactional
    public DateSocialEvent listById(Long id){
        DateSocialEvent dateSocialEvent=dateSocialEventRepository.findById(id).get();
        //dateSocialEvent.setZoneEventList(null);
        return dateSocialEvent;
    }
    @Transactional
    public void delete(Long id, boolean forced){
        DateSocialEvent dateSocialEvent= dateSocialEventRepository.findById(id).get();
        if(forced){
            List<ZoneEvent> zoneEventList = zoneEventRepository.findByDateSocialEvent_Id(id);
            for (ZoneEvent z: zoneEventList){
                zoneEventRepository.delete(z);
            }
        }
        dateSocialEventRepository.delete(dateSocialEvent);
    }
    @Transactional
    public List<DateSocialEvent> listAll() {
        List<DateSocialEvent> dateSocialEventList = dateSocialEventRepository.findAll();
        for(DateSocialEvent s: dateSocialEventList){
            s.getSocialEvent().setUser(null);
            s.getSocialEvent().setCategory(null);
            s.getSocialEvent().setDateSocialEventList(null);
            s.setZoneEventList(null);
        }
        return dateSocialEventList;
    }
    @Transactional
    public DateSocialEvent dateEventEnd() {
        DateSocialEvent dateSocialEvent = dateSocialEventRepository.dateEventEnd();
        dateSocialEvent.getSocialEvent().setDateSocialEventList(null);
        dateSocialEvent.getSocialEvent().getCategory().setSocialEventList(null);
        dateSocialEvent.getSocialEvent().getCategory().setUserCategoryList(null);
        dateSocialEvent.getSocialEvent().getCategory().setGroupList(null);
        dateSocialEvent.setZoneEventList(null);
        dateSocialEvent.getSocialEvent().getUser().setSocialEventList(null);
        dateSocialEvent.getSocialEvent().getUser().setGroupUserList(null);
        dateSocialEvent.getSocialEvent().getUser().setAuthorityList(null);
        dateSocialEvent.getSocialEvent().getUser().setPurchaseList(null);
        dateSocialEvent.getSocialEvent().getUser().setCardList(null);
        dateSocialEvent.getSocialEvent().getUser().setGroupList(null);
        dateSocialEvent.getSocialEvent().getUser().setUserCategoryList(null);
        return dateSocialEvent;
    }
    @Transactional
    public List<DTOZoneAvailableSummary> listZoneAvailableSummary(){

        List<SocialEvent>socialEventList=socialEventRepository.findAll();
        List<DateSocialEvent>dateSocialEventList=dateSocialEventRepository.findAll();
        List<ZoneEvent>zoneEventList=zoneEventRepository.findAll();

        List<DTOZoneAvailableSummary> dtoZoneAvailableSummaryList = new ArrayList<>();

        for(SocialEvent sE:  socialEventList) {
            for(DateSocialEvent dS:dateSocialEventList){
                String socialEventName = sE.getName() + " - " + sE.getCategory();

                DTOZoneAvailableSummary dtoZoneAvailableSummary= new DTOZoneAvailableSummary(socialEventName, dS.getZoneEventList().stream().toList());
                dtoZoneAvailableSummaryList.add(dtoZoneAvailableSummary);
            }
        }
        return dtoZoneAvailableSummaryList;
    }

    public List<DateSocialEvent> listBySocialEvent(Long id){
        List<DateSocialEvent> dateSocialEventList = dateSocialEventRepository.findDateSocialEventBySocialEvent(id);
        for(DateSocialEvent s: dateSocialEventList){
            s.getSocialEvent().setUser(null);
            s.getSocialEvent().setCategory(null);
            s.getSocialEvent().setDateSocialEventList(null);
            s.setZoneEventList(null);
        }
        return dateSocialEventList;
    }


}
