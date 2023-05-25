package main.pe.com.betweenAll.entities;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Entity

public class socialEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
