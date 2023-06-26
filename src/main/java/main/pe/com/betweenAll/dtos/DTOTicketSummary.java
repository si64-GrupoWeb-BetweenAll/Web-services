package main.pe.com.betweenAll.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class DTOTicketSummary {
    private Long idTicket;
    private String nameUser;
    private String nameSocialEvent;
    private String nameZoneEvent;
    private String dateSocialEvent;
    private String locationSocialEvent;
    private String emailUser;
    private Double amountPurchase;
}
