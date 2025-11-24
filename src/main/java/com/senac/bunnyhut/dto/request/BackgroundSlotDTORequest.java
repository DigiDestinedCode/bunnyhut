package com.senac.bunnyhut.dto.request;

public class BackgroundSlotDTORequest {
    private Integer backgroundId;
    private Integer furnitureId;

    public Integer getBackgroundId() {
        return backgroundId;
    }

    public void setBackgroundId(Integer backgroundId) {
        this.backgroundId = backgroundId;
    }

    public Integer getFurnitureId() {
        return furnitureId;
    }

    public void setFurnitureId(Integer furnitureId) {
        this.furnitureId = furnitureId;
    }
}
