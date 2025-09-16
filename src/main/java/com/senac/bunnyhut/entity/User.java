package com.senac.bunnyhut.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;
    @Column(name = "user_nickname")
    private String nickname;
    @Column(name = "user_email")
    private String email;
    @Column(name = "user_password_hash")
    private String password_hash;
    @Column(name = "user_coin")
    private Integer coin;
    @Column(name = "user_create_at")
    private LocalDateTime created_at;
    @Column(name = "user_status")
    private Integer status;
}
