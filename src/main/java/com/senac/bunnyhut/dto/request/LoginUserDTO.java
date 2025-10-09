package com.senac.bunnyhut.dto.request;

public record LoginUserDTO(
        String email,
        String password_hash) {
}
