package main.pe.com.betweenAll.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor

public class DTOEventsAssistedSummary {

    private String eventName;

    private Date eventDate;

    private String eventZone;
    private String userName;

}
