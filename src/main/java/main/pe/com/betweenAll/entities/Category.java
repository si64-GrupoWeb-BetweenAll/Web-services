package main.pe.com.betweenAll.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String state;

    @OneToMany(mappedBy = "category", cascade = {CascadeType.REMOVE})
    private List<Group> groupList;

    @OneToMany(mappedBy = "category", cascade = {CascadeType.REMOVE})
    private List<SocialEvent> socialEventList;

    @OneToMany(mappedBy = "category", cascade = {CascadeType.REMOVE})
    private List<UserCategory> userCategoryList;

    public Category(String name, String state) {
        this.name = name;
        this.state = state;
    }
}
