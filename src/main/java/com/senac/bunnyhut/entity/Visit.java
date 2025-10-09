package com.senac.bunnyhut.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @Transient
    @JsonProperty("idUser")
    private Integer idUser;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Transient
    @JsonProperty("idRabbit")
    private Integer idRabbit;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "rabbit_id", nullable = false)
    private Rabbit rabbit;

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

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getIdRabbit() {
        return idRabbit;
    }

    public void setIdRabbit(Integer idRabbit) {
        this.idRabbit = idRabbit;
    }

    public Rabbit getRabbit() {
        return rabbit;
    }

    public void setRabbit(Rabbit rabbit) {
        this.rabbit = rabbit;
    }
}
