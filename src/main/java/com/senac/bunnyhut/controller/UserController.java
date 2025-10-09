package com.senac.bunnyhut.controller;

import com.senac.bunnyhut.dto.request.UserDTOLoginRequest;
import com.senac.bunnyhut.dto.request.UserDTORequest;
import com.senac.bunnyhut.dto.response.UserDTOLoginResponse;
import com.senac.bunnyhut.dto.response.UserDTOResponse;
import com.senac.bunnyhut.dto.response.UserDTOUpdateResponse;
import com.senac.bunnyhut.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/listar")
    @Operation(
            summary = "Listar users",
            description = "Endpoint para listar todos os users"
    )
    public ResponseEntity<List<UserDTOResponse>> listUsers() {
        return ResponseEntity.ok(userService.listUsers());
    }

    @GetMapping("/listarPorUserId/{userId}")
    @Operation(
            summary = "Listar user pelo id de user",
            description = "Endpoint para listar user por Id de user"
    )
    public ResponseEntity<UserDTOResponse> listarPorUserId(@PathVariable("userId") Integer userId) {
        UserDTOResponse user = userService.listarPorUserId(userId);
        if (user == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(user);
        }
    }
//    @PostMapping("/criar")
//    @Operation(
//            summary = "Criar novo user",
//            description = "Endpoint para criar um novo registro de user"
//    )
//    public ResponseEntity<UserDTOResponse> criarUser(
//            @Valid @RequestBody UserDTORequest user
//    ) {
//        return ResponseEntity.status(HttpStatus.CREATED).body(userService.criarUser(user));
//    }

    @PutMapping("/atualizar/{userId}")
    @Operation(
            summary = "Atualizar todos os dados do user",
            description = "Endpoint para atualizar o registro de user"
    )
    public ResponseEntity<UserDTOResponse> atualizarUser(
            @PathVariable("userId") Integer userId,
            @Valid @RequestBody UserDTORequest userDTORequest
    ) {
        return ResponseEntity.ok(userService.atualizarUser(userId, userDTORequest));
    }

    @DeleteMapping("/apagar/{userId}")
    @Operation(
            summary = "Apagar registro do user",
            description = "Endpoint para apagar registro do user"
    )
    public ResponseEntity<Void> apagarUser(@PathVariable("userId") Integer userId) {
        userService.apagarUser(userId);
        return ResponseEntity.noContent().build();
    }



    @PostMapping("/login")
    public ResponseEntity<UserDTOLoginResponse>  login(@RequestBody UserDTOLoginRequest userDTOLoginRequest) {
        return ResponseEntity.ok(userService.login(userDTOLoginRequest));
    }
    @PostMapping("/criar")
    public ResponseEntity<UserDTOResponse> criar(@RequestBody UserDTORequest userDTORequest) {
        return ResponseEntity.ok(userService.criar(userDTORequest));
    }
}
