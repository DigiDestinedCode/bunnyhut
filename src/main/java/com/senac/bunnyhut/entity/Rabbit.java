package com.senac.bunnyhut.entity;

import jakarta.persistence.*;

@Entity
@Table(name="")
public class Rabbit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rabbit_id")
    private Integer id;
    @Column(name = "rabbit_name")
    private String name;
    @Column(name = "rabbit_description")
    private String description;
    @Column(name = "rabbit_default_img")
    private String default_img;
    @Column(name = "rabbit_alt_img")
    private String alt_img;
}
