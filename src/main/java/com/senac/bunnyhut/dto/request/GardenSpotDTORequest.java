package com.senac.bunnyhut.dto.request;

import java.time.LocalDateTime;

public class GardenSpotDTORequest {
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

    public LocalDateTime getPlantedAt() {
        return planted_at;
    }

    public void setPlantedAt(LocalDateTime planted_at) {
        this.planted_at = planted_at;
    }

    public Enum getGrowthStage() {
        return growth_stage;
    }

    public void setGrowthStage(Enum growth_stage) {
        this.growth_stage = growth_stage;
    }
}
