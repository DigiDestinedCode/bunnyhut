package com.senac.bunnyhut.entity;

import jakarta.persistence.*;

import java.util.Set;

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

    @OneToMany(mappedBy = "rabbit")
    private Set<Visit> visits;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDefault_img() {
        return default_img;
    }

    public void setDefault_img(String default_img) {
        this.default_img = default_img;
    }

    public String getAlt_img() {
        return alt_img;
    }

    public void setAlt_img(String alt_img) {
        this.alt_img = alt_img;
    }

    public Set<Visit> getVisits() {
        return visits;
    }

    public void setVisits(Set<Visit> visits) {
        this.visits = visits;
    }
}
