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
}
