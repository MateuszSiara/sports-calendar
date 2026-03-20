package com.sportradar.sportscalendar.repository;

import com.sportradar.sportscalendar.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Integer> {
}