package com.sportradar.sportscalendar.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "events")
@Getter
@Setter

public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "season_year", nullable = false)
    private Integer seasonYear;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "date_venue", nullable = false)
    private LocalDate dateVenue;

    @Column(name = "time_venue_utc", nullable = false)
    private LocalTime timeVenueUtc;

    @Column(name = "event_group")
    private String eventGroup;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "_competition_id", nullable = false)
    private Competition competition;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "_stage_id", nullable = false)
    private Stage stage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "_stadium_id")
    private Stadium stadium;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "_home_team_id")
    private Team homeTeam;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "_away_team_id")
    private Team awayTeam;

    @OneToOne(mappedBy = "event", fetch = FetchType.LAZY)
    private Result result;

}