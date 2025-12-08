package com.senac.bunnyhut.dto.response;

import java.time.LocalDateTime;

public class UserDTOResponse {
    private Integer id;
    private String nickname;
    private String email;
    private String password_hash;
    private Integer coin;
    private LocalDateTime created_at;
    private Integer status;

    public Integer getid() {
        return id;
    }

    public void setid(Integer id) {
        this.id = id;
    }

    public String getnickname() {
        return nickname;
    }

    public void setnickname(String nickname) {
        this.nickname = nickname;
    }

    public String getemail() {
        return email;
    }

    public void setemail(String email) {
        this.email = email;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    public Integer getcoin() {
        return coin;
    }

    public void setcoin(Integer coin) {
        this.coin = coin;
    }

    public LocalDateTime getcreated_at() {
        return created_at;
    }

    public void setcreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public Integer getstatus() {
        return status;
    }

    public void setstatus(Integer status) {
        this.status = status;
    }
}
