package com.senac.bunnyhut.entity;

import jakarta.persistence.*;

@Entity
@Table(name="garden")
public class Garden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "garden_id")
    private Integer id;
    @Column(name = "garden_name")
    private String name;
}
