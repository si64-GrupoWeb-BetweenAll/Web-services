package main.pe.com.betweenAll.repositories;

import main.pe.com.betweenAll.dtos.DTOSocialEventsAvailableSummary;
import main.pe.com.betweenAll.entities.GroupUser;
import main.pe.com.betweenAll.entities.SocialEvent;
import main.pe.com.betweenAll.entities.Ticket;
import main.pe.com.betweenAll.entities.User;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SocialEventRepository extends JpaRepository <SocialEvent, Long> {
    List<SocialEvent> findByUser_Id(Long id);

    @Query(value = "SELECT s.name AS nameSocialEvent,\n" +
            "SUM(z.capacity) AS amountTotalTickets, COUNT(i.id) AS amountBuyTickets,\n" +
            "(SUM(z.capacity) - COUNT(i.id)) AS amountAvailableTickets \n" +
            "FROM socials_events s\n" +
            "JOIN date_social_events d ON s.id = d.social_event_id\n" +
            "JOIN zones_events z ON d.id = z.date_social_event_id\n" +
            "JOIN tickets i ON z.id = i.zone_event_id\n" +
            "GROUP BY s.name", nativeQuery = true)
    List<String[]> listSocialEventAvailableSummary();

    @Query(value = "SELECT * FROM socials_events u WHERE u.user_id = :id", nativeQuery = true)
    List<SocialEvent> findSocialEventByUser(@Param("id") Long id);

}
