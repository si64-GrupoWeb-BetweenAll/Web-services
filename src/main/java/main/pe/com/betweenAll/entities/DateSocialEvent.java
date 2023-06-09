package main.pe.com.betweenAll.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name="date_social_events")
@Data
@NoArgsConstructor

public class DateSocialEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private LocalTime  starTime;
    private LocalTime endTime;
    @ManyToOne
    @JoinColumn(name = "social_event_id")
    private SocialEvent socialEvent;

    @OneToMany(mappedBy = "dateSocialEvent", cascade = {CascadeType.REMOVE})
    private List<ZoneEvent> zoneEventList;

    public DateSocialEvent(Date date, LocalTime starTime, LocalTime endTime, SocialEvent socialEvent) {
        this.date = date;
        this.starTime = starTime;
        this.endTime = endTime;
        this.socialEvent = socialEvent;
    }
}
