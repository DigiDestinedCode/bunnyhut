package com.senac.bunnyhut.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name="backgrounds_slot")
public class BackgroundSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "backgrounds_slots_id")
    private int id;

    @Transient
    @JsonProperty("idFurniture")
    private Integer idFurniture;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "furniture_id", nullable = false)
    private Furniture furniture;

    @Transient
    @JsonProperty("idBackground")
    private Integer idBackground;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "background_id", nullable = false)
    private Background background;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getIdFurniture() {
        return idFurniture;
    }

    public void setIdFurniture(Integer idFurniture) {
        this.idFurniture = idFurniture;
    }

    public Furniture getFurniture() {
        return furniture;
    }

    public void setFurniture(Furniture furniture) {
        this.furniture = furniture;
    }

    public Integer getIdBackground() {
        return idBackground;
    }

    public void setIdBackground(Integer idBackground) {
        this.idBackground = idBackground;
    }

    public Background getBackground() {
        return background;
    }

    public void setBackground(Background background) {
        this.background = background;
    }
}
