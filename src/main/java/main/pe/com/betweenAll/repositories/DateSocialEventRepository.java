package main.pe.com.betweenAll.repositories;
import main.pe.com.betweenAll.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import main.pe.com.betweenAll.entities.DateSocialEvent;

import java.util.List;

@Repository

public interface DateSocialEventRepository extends JpaRepository<DateSocialEvent, Long> {
    List<DateSocialEvent> findBySocialEvent_Id(Long id);

}

