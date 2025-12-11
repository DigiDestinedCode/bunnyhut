package com.senac.bunnyhut.dto.request;

import java.time.LocalDateTime;

public class VisitDTORequest {

    private Integer id;
    private Integer userId;
    private Integer rabbitId;
    private LocalDateTime date;
    private Integer qty;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRabbitId() {
        return rabbitId;
    }

    public void setRabbitId(Integer rabbitId) {
        this.rabbitId = rabbitId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
}
