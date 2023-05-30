package main.pe.com.betweenAll.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="tickets")
@Data
@NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="purchase_id")
    private Purchase purchase;
    @ManyToOne
    @JoinColumn(name="zone_event_id")
    private ZoneEvent zoneEvent;

    public Ticket(Purchase purchase, ZoneEvent zoneEvent) {
        this.purchase = purchase;
        this.zoneEvent = zoneEvent;
    }
}
