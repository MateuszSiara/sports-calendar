package com.sportradar.sportscalendar.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "competitions")
public class Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "origin_competition_id", nullable = false)
    private String originCompetitionId;

    @Column(name = "origin_competition_name", nullable = false)
    private String originCompetitionName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "_sport_id", nullable = false)
    private Sport sport;
}
