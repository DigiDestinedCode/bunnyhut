package com.senac.bunnyhut.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="transaction_coin")
public class Transaction_Coin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_coin_id")
    private Integer id;
    @Column(name = "transaction_type")
    private EnumType type;
    @Column(name = "transaction_value")
    private Integer value;
    @Column(name = "transaction_description")
    private String description;
    @Column(name = "transaction_date")
    private LocalDateTime date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EnumType getType() {
        return type;
    }

    public void setType(EnumType type) {
        this.type = type;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
