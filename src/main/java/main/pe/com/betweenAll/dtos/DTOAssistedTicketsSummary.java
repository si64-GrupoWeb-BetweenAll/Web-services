package main.pe.com.betweenAll.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import main.pe.com.betweenAll.entities.Ticket;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor

public class DTOAssistedTicketsSummary {

    private String completeName;
    private Integer countAssistedTickets;
    @OneToMany(mappedBy = "dtoAssistedTicketsSummary", cascade = {CascadeType.REMOVE})
    List<Ticket> ticketList;

}
