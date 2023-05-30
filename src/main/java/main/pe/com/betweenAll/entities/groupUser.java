package main.pe.com.betweenAll.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="groupUser")
@Data
@NoArgsConstructor
public class groupUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private user user;

    @ManyToOne
    @JoinColumn(name="group_id")
    private Group group;

    public groupUser(main.pe.com.betweenAll.entities.user user, Group group) {
        this.user = user;
        this.group = group;
    }
}
