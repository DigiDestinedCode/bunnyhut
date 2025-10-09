package com.senac.bunnyhut.dto.response;

import java.time.LocalDateTime;

public class VisitDTOResponse {

    private Integer id;
    private Integer userId;
    private Integer rabbitId;
    private LocalDateTime date;
    private Integer qty;

    public Integer getid() {
        return id;
    }

    public void setid(Integer id) {
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

    public LocalDateTime getdate() {
        return date;
    }

    public void setdate(LocalDateTime date) {
        this.date = date;
    }

    public Integer getqty() {
        return qty;
    }

    public void setqty(Integer qty) {
        this.qty = qty;
    }
}
