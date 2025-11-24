package com.senac.bunnyhut.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Set;

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

    @Transient
    @JsonProperty("idItem")
    private Integer idItem;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @OneToMany(mappedBy = "plant")
    private Set<GardenSpot> garden_spots;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGrowth_stages() {
        return growth_stages;
    }

    public void setGrowth_stages(Integer growth_stages) {
        this.growth_stages = growth_stages;
    }

    public Integer getHarvest_value() {
        return harvest_value;
    }

    public void setHarvest_value(Integer harvest_value) {
        this.harvest_value = harvest_value;
    }

    public Integer getHarvestable() {
        return harvestable;
    }

    public void setHarvestable(Integer harvestable) {
        this.harvestable = harvestable;
    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Set<GardenSpot> getGarden_spots() {
        return garden_spots;
    }

    public void setGarden_spots(Set<GardenSpot> garden_spots) {
        this.garden_spots = garden_spots;
    }
}
