package com.sportradar.sportscalendar.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "stages")

public class Stage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "stage_code", nullable = false)
    private String stageCode;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "ordering", nullable = false)
    private Integer ordering;
}
