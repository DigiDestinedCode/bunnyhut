package com.senac.bunnyhut.entity;

import jakarta.persistence.*;

@Entity
@Table(name="furniture")
public class Furniture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "furniture_id")
    private Integer id;
    @Column(name = "furniture_slot")
    private String slot;
}
