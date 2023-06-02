package main.pe.com.betweenAll.repositories;

import main.pe.com.betweenAll.entities.ZoneEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ZoneEventRepository extends JpaRepository<ZoneEvent, Long> {
    List<ZoneEvent> findByDateSocialEvent_Id(Long id);
}
