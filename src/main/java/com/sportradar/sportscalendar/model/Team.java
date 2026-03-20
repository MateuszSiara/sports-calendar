package com.sportradar.sportscalendar.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "teams")
@Getter
@Setter
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "official_name", nullable = false)
    private String officialName;

    @Column(name = "slug", nullable = false)
    private String slug;

    @Column(name = "abbreviation", nullable = false)
    private String abbreviation;

    @Column(name = "team_country_code", nullable = false)
    private String teamCountryCode;
}