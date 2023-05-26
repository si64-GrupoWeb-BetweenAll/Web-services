package main.pe.com.betweenAll.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
