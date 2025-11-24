package com.senac.bunnyhut.dto.response;

import java.time.LocalDateTime;

public class TransactionCoinDTOResponse {
    private Integer id;
    private Integer userId;
    private Enum transaction_type;
    private Integer transaction_value;
    private String transaction_description;
    private LocalDateTime transaction_date;

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

    public Enum getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(Enum transaction_type) {
        this.transaction_type = transaction_type;
    }

    public Integer getTransaction_value() {
        return transaction_value;
    }

    public void setTransaction_value(Integer transaction_value) {
        this.transaction_value = transaction_value;
    }

    public String getTransaction_description() {
        return transaction_description;
    }

    public void setTransaction_description(String transaction_description) {
        this.transaction_description = transaction_description;
    }

    public LocalDateTime getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(LocalDateTime transaction_date) {
        this.transaction_date = transaction_date;
    }
}