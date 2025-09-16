package com.senac.bunnyhut.entity;

import jakarta.persistence.*;

@Entity
@Table(name="background")
public class Background {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "background_id")
    private Integer id;
    @Column(name = "background_theme")
    private String theme;
}
