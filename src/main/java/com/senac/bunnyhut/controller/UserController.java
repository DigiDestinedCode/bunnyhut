package com.senac.bunnyhut.controller;

import com.senac.bunnyhut.dto.request.UserDTOLoginRequest;
import com.senac.bunnyhut.dto.request.UserDTORequest;
import com.senac.bunnyhut.dto.response.UserDTOLoginResponse;
import com.senac.bunnyhut.dto.response.UserDTOResponse;
import com.senac.bunnyhut.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
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

    @GetMapping("/list")
    @Operation(
            summary = "List all users",
            description = "Endpoint to list all users"
    )
    public ResponseEntity<List<UserDTOResponse>> listUsers() {
        return ResponseEntity.ok(userService.listUsers());
    }

    @GetMapping("/listById/{userId}")
    @Operation(
            summary = "List user by ID",
            description = "Endpoint to list user by ID"
    )
    public ResponseEntity<UserDTOResponse> getUserById(@PathVariable("userId") Integer userId) {
        UserDTOResponse user = userService.getUserById(userId);
        if (user == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(user);
        }
    }

    @PutMapping("/update/{userId}")
    @Operation(
            summary = "Update all user data",
            description = "Endpoint to update the user record"
    )
    public ResponseEntity<UserDTOResponse> updateUser(
            @PathVariable("userId") Integer userId,
            @Valid @RequestBody UserDTORequest userDTORequest
    ) {
        return ResponseEntity.ok(userService.updateUser(userId, userDTORequest));
    }

    @DeleteMapping("/delete/{userId}")
    @Operation(
            summary = "Delete user record (logical deletion)",
            description = "Endpoint to logically delete user record"
    )
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") Integer userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTOLoginResponse>  login(@RequestBody UserDTOLoginRequest userDTOLoginRequest) {
        return ResponseEntity.ok(userService.login(userDTOLoginRequest));
    }

    @PostMapping("/create")
    public ResponseEntity<UserDTOResponse> create(@RequestBody UserDTORequest userDTORequest) {
        return ResponseEntity.ok(userService.create(userDTORequest));
    }
}