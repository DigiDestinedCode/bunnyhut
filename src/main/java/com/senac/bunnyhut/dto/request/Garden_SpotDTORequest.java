package com.senac.bunnyhut.dto.request;

import java.time.LocalDateTime;

public class Garden_SpotDTORequest {
    private Integer gardenId;
    private Integer plantId;
    private LocalDateTime planted_at;
    private Enum growth_stage;

    public Integer getGardenId() {
        return gardenId;
    }

    public void setGardenId(Integer gardenId) {
        this.gardenId = gardenId;
    }

    public Integer getPlantId() {
        return plantId;
    }

    public void setPlantId(Integer plantId) {
        this.plantId = plantId;
    }

    public LocalDateTime getPlanted_at() {
        return planted_at;
    }

    public void setPlanted_at(LocalDateTime planted_at) {
        this.planted_at = planted_at;
    }

    public Enum getGrowth_stage() {
        return growth_stage;
    }

    public void setGrowth_stage(Enum growth_stage) {
        this.growth_stage = growth_stage;
    }
}
