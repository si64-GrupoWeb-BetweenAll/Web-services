package main.pe.com.betweenAll.services;

import main.pe.com.betweenAll.dtos.DTOZoneAvailableSummary;
import main.pe.com.betweenAll.entities.DateSocialEvent;
import main.pe.com.betweenAll.entities.SocialEvent;

import java.util.Date;
import java.util.List;
public interface DateSocialEventService {

    public DateSocialEvent save(DateSocialEvent dateSocialEvent,Long idSocialEvent);
    public void delete(Long id, boolean forced);
    public DateSocialEvent listById(Long id);
    public List<DateSocialEvent> listAll();
    public List<DateSocialEvent> listBySocialEvent(Long id);
    public List<DTOZoneAvailableSummary> listZoneAvailableSummary();
    public DateSocialEvent dateEventEnd();
}
