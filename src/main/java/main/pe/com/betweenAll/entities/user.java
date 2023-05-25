package main.pe.com.betweenAll.entities;

import javax.persistence.*;
@Entity
public class user {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
