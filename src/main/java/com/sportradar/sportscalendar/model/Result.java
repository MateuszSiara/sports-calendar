package com.sportradar.sportscalendar.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "results")
@Getter
@Setter
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "home_goals", nullable = false)
    private Integer homeGoals;

    @Column(name = "away_goals", nullable = false)
    private Integer awayGoals;

    @Column(name = "winner")
    private String winner;

    @Column(name = "message")
    private String message;

    @Column(name = "score_by_periods", columnDefinition = "json")
    private String scoreByPeriods;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "_winner_id")
    private Team winnerTeam;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "_event_id", nullable = false, unique = true)
    private Event event;
}