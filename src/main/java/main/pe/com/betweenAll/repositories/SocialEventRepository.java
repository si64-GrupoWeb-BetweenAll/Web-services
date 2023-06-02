package main.pe.com.betweenAll.repositories;

import main.pe.com.betweenAll.entities.GroupUser;
import main.pe.com.betweenAll.entities.SocialEvent;
import main.pe.com.betweenAll.entities.Ticket;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SocialEventRepository extends JpaRepository <SocialEvent, Long> {
    List<SocialEvent> findByUser_Id(Long id);
}
