package main.pe.com.betweenAll.services;


import main.pe.com.betweenAll.entities.ZoneEvent;

import java.util.List;

public interface ZoneEventService {
    public List<ZoneEvent> listAll();
    public List<ZoneEvent> listByName(String name);
    public ZoneEvent listById(Long id);

    public ZoneEvent save(ZoneEvent zoneEvent);

    public void delete(Long id, boolean forced);
}
