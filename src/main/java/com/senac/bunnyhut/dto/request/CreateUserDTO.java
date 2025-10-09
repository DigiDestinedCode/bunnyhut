package com.senac.bunnyhut.dto.request;

import com.senac.bunnyhut.entity.RoleName;

public record CreateUserDTO(
        String email,
        String password_hash,
        RoleName role
) {

}
