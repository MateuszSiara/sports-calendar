package com.sportradar.sportscalendar.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventDto {

    private Integer id;
    private Integer seasonYear;
    private String status;
    private LocalDate dateVenue;
    private LocalTime timeVenueUtc;
    private String eventGroup;

    private String sportName;

    private String competitionName;

    private String stageName;

    private String stadiumName;

    private String homeTeamName;
    private String awayTeamName;

    private Integer homeGoals;
    private Integer awayGoals;
    private String winner;
}