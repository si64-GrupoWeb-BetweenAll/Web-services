package main.pe.com.betweenAll.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="groups")
@Data
@NoArgsConstructor
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    private String image;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "group", cascade = {CascadeType.REMOVE})
    List<GroupUser> groupUserList;


    public Group(String name, String description, String image, Category category, User user) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.category = category;
        this.user=user;
    }
}

