package com.senac.bunnyhut.dto.request;

public class FurnitureDTORequest {
    private Integer itemId;
    private String furniture_slot;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getFurnitureSlot() {
        return furniture_slot;
    }

    public void setFurnitureSlot(String furniture_slot) {
        this.furniture_slot = furniture_slot;
    }
}
