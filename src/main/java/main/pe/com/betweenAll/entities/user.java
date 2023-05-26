package main.pe.com.betweenAll.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="user")
@Data
@NoArgsConstructor
public class user {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String lastname;
    private String typeDocument;
    private int numberDocument;
    private String phone;
    private String email;
    private String password;
    private String image;
    private String city;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.REMOVE})
    List<groupUser> groupUserList;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.REMOVE})
    List<userCategory> userCategoryList;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.REMOVE})
    List<socialEvent> socialEventList;

    public user(String name, String lastname, String typeDocument, int numberDocument, String phone, String email, String password, String image, String city) {
        this.name = name;
        this.lastname = lastname;
        this.typeDocument = typeDocument;
        this.numberDocument = numberDocument;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.image = image;
        this.city = city;
    }
}
