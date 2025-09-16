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
}
