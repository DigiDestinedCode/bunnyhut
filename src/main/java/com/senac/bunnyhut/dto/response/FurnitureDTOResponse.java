package com.senac.bunnyhut.dto.response;

public class FurnitureDTOResponse {
    private Integer id;
    private Integer itemId;
    private String slot;

    public Integer getid() {
        return id;
    }

    public void setid(Integer id) {
        this.id = id;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getslot() {
        return slot;
    }

    public void setslot(String slot) {
        this.slot = slot;
    }
}
