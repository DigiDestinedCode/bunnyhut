package com.senac.bunnyhut.entity;

import jakarta.persistence.*;

@Entity
@Table(name="plant")
public class Plant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plant_id")
    private Integer id;
    @Column(name = "plant_growth_stages")
    private Integer growth_stages;
    @Column(name = "plant_harvest_value")
    private Integer harvest_value;
    @Column(name = "plant_harvestable")
    private Integer harvestable;
}
