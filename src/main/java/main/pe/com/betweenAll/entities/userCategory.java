package main.pe.com.betweenAll.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name="userCategory")
@Data
@NoArgsConstructor

public class userCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private user user;

    @ManyToOne
    @JoinColumn(name = "idCategory")
    private category category;

}
