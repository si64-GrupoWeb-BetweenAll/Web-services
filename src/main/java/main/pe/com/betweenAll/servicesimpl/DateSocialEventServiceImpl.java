package main.pe.com.betweenAll.servicesimpl;

import main.pe.com.betweenAll.entities.DateSocialEvent;
import main.pe.com.betweenAll.entities.SocialEvent;
import main.pe.com.betweenAll.entities.ZoneEvent;
import main.pe.com.betweenAll.repositories.*;
import main.pe.com.betweenAll.services.DateSocialEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
        return dateSocialEventList;
    }



}
