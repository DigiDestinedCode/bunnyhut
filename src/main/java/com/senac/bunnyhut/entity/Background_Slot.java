package com.senac.bunnyhut.entity;

import jakarta.persistence.*;

@Entity
@Table(name="background_slot")
public class Background_Slot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "background_slots_id")
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
