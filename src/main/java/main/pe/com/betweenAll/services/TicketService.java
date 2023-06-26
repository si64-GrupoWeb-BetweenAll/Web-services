package main.pe.com.betweenAll.services;

import main.pe.com.betweenAll.dtos.DTOTicketSummary;
import main.pe.com.betweenAll.entities.Group;
import main.pe.com.betweenAll.entities.Ticket;

import java.util.List;
public interface TicketService {

    public List<Ticket> listAll();
    public Ticket listById(Long id);
    public Ticket save(Ticket ticket, Long idCategory, Long idZoneEvent);
    public void delete(Long id, boolean forced);
    public  List<DTOTicketSummary> listTicketByUserSummary(Long id);

}
