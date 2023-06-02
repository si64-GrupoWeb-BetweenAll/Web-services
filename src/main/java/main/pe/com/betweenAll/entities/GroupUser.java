package main.pe.com.betweenAll.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="group_user")
@Data
@NoArgsConstructor
public class GroupUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="group_id")
    private Group group;

    public GroupUser(User user, Group group) {
        this.user = user;
        this.group = group;
    }
}
