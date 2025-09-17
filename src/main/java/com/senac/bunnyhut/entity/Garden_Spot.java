package com.senac.bunnyhut.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="garden_spot")
public class Garden_Spot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "garden_spot_id")
    private Integer id;
    @Column(name = "planted_at")
    private LocalDateTime planted_at;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getPlanted_at() {
        return planted_at;
    }

    public void setPlanted_at(LocalDateTime planted_at) {
        this.planted_at = planted_at;
    }
}
