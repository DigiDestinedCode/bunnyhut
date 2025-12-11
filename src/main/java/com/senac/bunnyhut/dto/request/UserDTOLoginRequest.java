package com.senac.bunnyhut.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDTOLoginRequest {
    private String email;
    @JsonProperty("passwordHash")
    private String password_hash;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return password_hash;
    }

    public void setPasswordHash(String password_hash) {
        this.password_hash = password_hash;
    }
}
