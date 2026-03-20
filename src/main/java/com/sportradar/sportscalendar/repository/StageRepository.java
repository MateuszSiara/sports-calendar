package com.sportradar.sportscalendar.repository;

import com.sportradar.sportscalendar.model.Stage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StageRepository extends JpaRepository<Stage, Integer> {
}