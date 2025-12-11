package com.senac.bunnyhut.dto.response;

import java.time.LocalDateTime;

public class GardenSpotDTOResponse {
    private Integer id;
    private Integer gardenId;
    private Integer plantId;
    private LocalDateTime plantedAt;
    private Enum growthStage;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
        return plantedAt;
    }

    public void setPlantedAt(LocalDateTime plantedAt) {
        this.plantedAt = plantedAt;
    }

    public Enum getGrowthStage() {
        return growthStage;
    }

    public void setGrowthStage(Enum growthStage) {
        this.growthStage = growthStage;
    }
}