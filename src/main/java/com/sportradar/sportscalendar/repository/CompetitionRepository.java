package com.sportradar.sportscalendar.repository;

import com.sportradar.sportscalendar.model.Competition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitionRepository extends JpaRepository<Competition, Integer> {
}