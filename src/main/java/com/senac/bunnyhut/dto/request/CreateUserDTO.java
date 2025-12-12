package com.senac.bunnyhut.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record CreateUserDTO(
        String nickname,
        String email,
        @JsonProperty("passwordHash")
        String password_hash,
        List<String> roleList
) {

}
