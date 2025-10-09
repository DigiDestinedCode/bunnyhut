package com.senac.bunnyhut.dto.request;

import java.time.LocalDateTime;

public class Transaction_CoinDTORequest {
    private Integer userId;
    private Enum type;
    private Integer value;
    private String description;
    private LocalDateTime date;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Enum gettype() {
        return type;
    }

    public void settype(Enum type) {
        this.type = type;
    }

    public Integer getvalue() {
        return value;
    }

    public void setvalue(Integer value) {
        this.value = value;
    }

    public String getdescription() {
        return description;
    }

    public void setdescription(String description) {
        this.description = description;
    }

    public LocalDateTime getdate() {
        return date;
    }

    public void setdate(LocalDateTime date) {
        this.date = date;
    }
}
