package com.senac.bunnyhut.dto.response;

public class PlantDTOResponse {
    private Integer id;
    private Integer itemId;
    private Integer growth_stages;
    private Integer growth_time;
    private Integer harvest_value;
    private Integer harvestable;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getGrowthStages() {
        return growth_stages;
    }

    public void setGrowthStages(Integer growth_stages) {
        this.growth_stages = growth_stages;
    }

    public Integer getGrowthTime() {
        return growth_time;
    }

    public void setGrowthTime(Integer growth_time) {
        this.growth_time = growth_time;
    }

    public Integer getHarvestValue() {
        return harvest_value;
    }

    public void setHarvestValue(Integer harvest_value) {
        this.harvest_value = harvest_value;
    }

    public Integer getHarvestable() {
        return harvestable;
    }

    public void setHarvestable(Integer harvestable) {
        this.harvestable = harvestable;
    }
}
