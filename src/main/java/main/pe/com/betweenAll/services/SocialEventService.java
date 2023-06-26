package main.pe.com.betweenAll.services;


import main.pe.com.betweenAll.dtos.DTOSocialEventsAvailableSummary;
import main.pe.com.betweenAll.entities.SocialEvent;

import java.util.List;
public interface SocialEventService {

    public List<SocialEvent> listAll();
    public List<SocialEvent> listByCreated(Long id);
    public SocialEvent listById(Long id);
    public SocialEvent save(SocialEvent socialEvent);
    public void delete(Long id, boolean forced);
    public List<DTOSocialEventsAvailableSummary> listSocialEventsAvailableSummary();

}
