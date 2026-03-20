package com.sportradar.sportscalendar.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cards")
@Getter
@Setter
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "card_type", nullable = false)
    private String cardType;

    @Column(name = "minute")
    private Integer minute;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "_event_id", nullable = false)
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "_team_id")
    private Team team;
}