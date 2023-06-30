package main.pe.com.betweenAll.repositories;
import main.pe.com.betweenAll.entities.SocialEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import main.pe.com.betweenAll.entities.DateSocialEvent;

import java.util.Date;
import java.util.List;

@Repository

public interface DateSocialEventRepository extends JpaRepository<DateSocialEvent, Long> {
    List<DateSocialEvent> findBySocialEvent_Id(Long id);
    @Query(value = "SELECT * FROM date_social_events d WHERE d.social_event_id = :idEvent", nativeQuery = true)
    List<DateSocialEvent> findDateSocialEventBySocialEvent(@Param("idEvent") Long idEvent);

    @Query(value = "SELECT*FROM date_social_events WHERE id = (SELECT MAX(id) FROM date_social_events)", nativeQuery = true)
    public DateSocialEvent dateEventEnd();
}

