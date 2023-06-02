package main.pe.com.betweenAll.repositories;

import main.pe.com.betweenAll.entities.Card;
import main.pe.com.betweenAll.entities.UserCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findByUser_Id(Long id);
}
