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
    public DateSocialEvent save(DateSocialEvent dateSocialEvent){
        SocialEvent socialEventFound = socialEventRepository.findById(dateSocialEvent.getSocialEvent().getId()).get();
        DateSocialEvent newDataSocialEvent= new DateSocialEvent(dateSocialEvent.getDate(),dateSocialEvent.getStarTime(),
                dateSocialEvent.getEndTime(),socialEventFound);
        dateSocialEventRepository.save(newDataSocialEvent);
        return newDataSocialEvent;
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



}
