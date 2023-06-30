package main.pe.com.betweenAll.repositories;


import main.pe.com.betweenAll.entities.DateSocialEvent;
import main.pe.com.betweenAll.entities.ZoneEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ZoneEventRepository extends JpaRepository<ZoneEvent, Long> {
    List<ZoneEvent> findByDateSocialEvent_Id(Long id);
    List<ZoneEvent> findByNameContaining(String name);
    @Query(value = "SELECT * FROM zones_events z WHERE z.date_social_event_id = :idDate", nativeQuery = true)
    List<ZoneEvent> findZoneEventByDateEvent(@Param("idDate") Long idDate);
}
