package main.pe.com.betweenAll.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;
import java.time.LocalTime;
@Entity
@Table(name="dateSocialEvent")
@Data
@NoArgsConstructor

public class dateSocialEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private LocalTime  starTime;
    private LocalTime endTime;
    @ManyToOne
    @JoinColumn(name = "idSocialEvent")
    private socialEvent socialEvent;


    public dateSocialEvent(Date date, LocalTime starTime, LocalTime endTime) {
        this.date = date;
        this.starTime = starTime;
        this.endTime = endTime;
    }
}
