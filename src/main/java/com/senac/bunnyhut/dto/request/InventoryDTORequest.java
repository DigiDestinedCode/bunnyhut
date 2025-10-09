package com.senac.bunnyhut.dto.request;

public class InventoryDTORequest {
    private Integer userId;
    private Integer itemId;
    private Integer qty;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getqty() {
        return qty;
    }

    public void setqty(Integer qty) {
        this.qty = qty;
    }
}
