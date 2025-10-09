package com.senac.bunnyhut.dto.request;

public class PlantDTORequest {
    private Integer itemId;
    private Integer growth_stages;
    private Integer growth_time;
    private Integer harvest_value;
    private Integer harvestable;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getgrowth_stages() {
        return growth_stages;
    }

    public void setgrowth_stages(Integer growth_stages) {
        this.growth_stages = growth_stages;
    }

    public Integer getgrowth_time() {
        return growth_time;
    }

    public void setgrowth_time(Integer growth_time) {
        this.growth_time = growth_time;
    }

    public Integer getharvest_value() {
        return harvest_value;
    }

    public void setharvest_value(Integer harvest_value) {
        this.harvest_value = harvest_value;
    }

    public Integer getharvestable() {
        return harvestable;
    }

    public void setharvestable(Integer harvestable) {
        this.harvestable = harvestable;
    }
}
