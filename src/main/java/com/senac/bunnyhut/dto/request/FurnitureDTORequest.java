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

    public String getFurniture_slot() {
        return furniture_slot;
    }

    public void setFurniture_slot(String furniture_slot) {
        this.furniture_slot = furniture_slot;
    }
}
