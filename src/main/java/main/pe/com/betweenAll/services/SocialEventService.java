package main.pe.com.betweenAll.services;


import main.pe.com.betweenAll.dtos.DTOSocialEventSummary;
import main.pe.com.betweenAll.dtos.DTOSocialEventsAvailableSummary;
import main.pe.com.betweenAll.entities.SocialEvent;

import java.util.List;
public interface SocialEventService {

    public List<SocialEvent> listAll();
    public List<SocialEvent> listByCreated(Long id);
    public SocialEvent listById(Long id);
    public SocialEvent save(SocialEvent socialEvent,Long idCategory,Long idUser);
    public void delete(Long id, boolean forced);
    public List<DTOSocialEventSummary> listSocialEventSummary();
    public List<DTOSocialEventsAvailableSummary> listSocialEventsAvailableSummary();

}
