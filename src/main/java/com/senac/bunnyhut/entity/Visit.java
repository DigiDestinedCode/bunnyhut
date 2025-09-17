package com.senac.bunnyhut.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="visit")
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "visit_id")
    private Integer id;
    @Column(name = "visit_date")
    private LocalDateTime date;
    @Column(name = "visit_qty")
    private Integer qty;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
