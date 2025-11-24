package com.senac.bunnyhut.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="garden_spot")
public class GardenSpot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "garden_spot_id")
    private Integer id;
    @Column(name = "planted_at")
    private LocalDateTime planted_at;

    @Transient
    @JsonProperty("idGarden")
    private Integer idGarden;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "garden_id", nullable = false)
    private Garden garden;

    @Transient
    @JsonProperty("idPlant")
    private Integer idPlant;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "plant_id", nullable = false)
    private Plant plant;

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

    public Integer getIdGarden() {
        return idGarden;
    }

    public void setIdGarden(Integer idGarden) {
        this.idGarden = idGarden;
    }

    public Garden getGarden() {
        return garden;
    }

    public void setGarden(Garden garden) {
        this.garden = garden;
    }

    public Integer getIdPlant() {
        return idPlant;
    }

    public void setIdPlant(Integer idPlant) {
        this.idPlant = idPlant;
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }
}
