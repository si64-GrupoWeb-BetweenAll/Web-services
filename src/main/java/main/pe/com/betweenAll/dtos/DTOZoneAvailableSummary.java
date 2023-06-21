package main.pe.com.betweenAll.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import main.pe.com.betweenAll.entities.ZoneEvent;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@AllArgsConstructor
public class DTOZoneAvailableSummary {

    private String socialEventName;
    @OneToMany(mappedBy = "dtoZoneAvailable", cascade = {CascadeType.REMOVE})
    private List<ZoneEvent> zoneEventList;

}
