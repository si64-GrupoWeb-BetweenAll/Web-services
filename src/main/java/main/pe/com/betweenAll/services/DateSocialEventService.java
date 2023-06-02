package main.pe.com.betweenAll.services;

import main.pe.com.betweenAll.entities.DateSocialEvent;

import java.util.List;
public interface DateSocialEventService {

    public DateSocialEvent save(DateSocialEvent dateSocialEvent);
    public void delete(Long id, boolean forced);
    public DateSocialEvent listById(Long id);
    public List<DateSocialEvent> listAll();
}
