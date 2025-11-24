package com.senac.bunnyhut.dto.response;

public class Background_SlotDTOResponse {
    private Integer id;
    private Integer backgroundId;
    private Integer furnitureId;

    public Integer getid() {
        return id;
    }

    public void setid(Integer id) {
        this.id = id;
    }

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
