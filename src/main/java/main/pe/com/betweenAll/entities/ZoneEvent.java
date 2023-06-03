package main.pe.com.betweenAll.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="zones_events")
@Data
@NoArgsConstructor
public class ZoneEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double price;
    private Integer capacity;

    @ManyToOne
    @JoinColumn(name = "date_social_event_id")
    private DateSocialEvent dateSocialEvent;
    @OneToMany(mappedBy = "zone_event", cascade = {CascadeType.REMOVE})
    List<Ticket> ticketList;

    //public ZoneEvent(DateSocialEvent dateSocialEvent) {
      //  this.dateSocialEvent = dateSocialEvent;
    //}

    public ZoneEvent(String name, Double price, Integer capacity, DateSocialEvent dateSocialEvent) {
        this.name = name;
        this.price = price;
        this.capacity = capacity;
        this.dateSocialEvent = dateSocialEvent;
    }
}
