package main.pe.com.betweenAll.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="socials_events")
@Data
@NoArgsConstructor

public class SocialEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String image;
    private String location;
    private Long description;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;
    @OneToMany(mappedBy = "social_event", cascade = {CascadeType.REMOVE})
    List<DateSocialEvent> dateSocialEventList;

    public SocialEvent(String name, String image, String location, Long description, User user, Category category) {
        this.name = name;
        this.image = image;
        this.location = location;
        this.description = description;
        this.user = user;
        this.category = category;
    }
}
