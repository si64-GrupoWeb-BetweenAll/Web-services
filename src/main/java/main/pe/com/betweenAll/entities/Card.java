package main.pe.com.betweenAll.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="cards")
@Data
@NoArgsConstructor
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long number;
    private Integer cvv;
    private Date dueDate;
    private String state;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "card", cascade = {CascadeType.REMOVE})
    List<Purchase> purchaseList;

    public Card(String name, Long number, Integer cvv, Date dueDate, String state, User user) {
        this.name = name;
        this.number = number;
        this.cvv = cvv;
        this.dueDate = dueDate;
        this.state = state;
        this.user = user;
    }
}
