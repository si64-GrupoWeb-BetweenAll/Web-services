package main.pe.com.betweenAll.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DTOSocialEventsAvailableSummary {
    private String nameSocialEvent;
    private Integer amountTotalTickets;
    private Integer amountBuyTickets;
    private Integer amountAvailableTickets;

}
