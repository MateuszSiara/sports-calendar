package com.sportradar.sportscalendar.repository;

import com.sportradar.sportscalendar.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Integer> {

    @Query("SELECT e FROM Event e " +
            "JOIN FETCH e.competition c " +
            "JOIN FETCH c.sport " +
            "JOIN FETCH e.stage " +
            "LEFT JOIN FETCH e.stadium " +
            "LEFT JOIN FETCH e.homeTeam " +
            "LEFT JOIN FETCH e.awayTeam " +
            "LEFT JOIN FETCH e.result")
    List<Event> findAllWithDetails();

    @Query("SELECT e FROM Event e " +
            "JOIN FETCH e.competition c " +
            "JOIN FETCH c.sport " +
            "JOIN FETCH e.stage " +
            "LEFT JOIN FETCH e.stadium " +
            "LEFT JOIN FETCH e.homeTeam " +
            "LEFT JOIN FETCH e.awayTeam " +
            "LEFT JOIN FETCH e.result " +
            "WHERE e.id = :id")
    Optional<Event> findByIdWithDetails(Integer id);
}