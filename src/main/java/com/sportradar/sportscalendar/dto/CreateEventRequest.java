package com.sportradar.sportscalendar.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
public class CreateEventRequest {

    private Integer seasonYear;
    private String status;
    private LocalDate dateVenue;
    private LocalTime timeVenueUtc;
    private String eventGroup;

    private Integer sportId;
    private Integer competitionId;
    private Integer stageId;
    private Integer stadiumId;
    private Integer homeTeamId;
    private Integer awayTeamId;
}