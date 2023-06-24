package main.pe.com.betweenAll.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="purchases")
@Data
@NoArgsConstructor
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private Long quantity;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name="card_id")
    private Card card;
    @OneToMany(mappedBy = "purchase", cascade = {CascadeType.REMOVE})
    List<Ticket> ticketList;

    public Purchase(Long quantity, Date date, User user, Card card) {
        this.date = date;
        this.quantity = quantity;
        this.user = user;
        this.card = card;
    }
}
