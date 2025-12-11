package com.senac.bunnyhut.controller;

import com.senac.bunnyhut.dto.request.RabbitDTORequest;
import com.senac.bunnyhut.dto.response.RabbitDTOResponse;
import com.senac.bunnyhut.service.RabbitService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/rabbit")
public class RabbitController {

    private final RabbitService rabbitService;

    public RabbitController(RabbitService rabbitService) {
        this.rabbitService = rabbitService;
    }

    @GetMapping("/list")
    @Operation(
            summary = "List all rabbits",
            description = "Endpoint to list all rabbits"
    )
    public ResponseEntity<List<RabbitDTOResponse>> listRabbits() {
        return ResponseEntity.ok(rabbitService.listRabbits());
    }

    @GetMapping("/listById/{rabbitId}")
    @Operation(
            summary = "List rabbit by ID",
            description = "Endpoint to list rabbit by ID"
    )
    public ResponseEntity<RabbitDTOResponse> getRabbitById(@PathVariable("rabbitId") Integer rabbitId) {
        RabbitDTOResponse rabbit = rabbitService.getRabbitById(rabbitId);
        if (rabbit == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(rabbit);
        }
    }
    @PostMapping("/create")
    @Operation(
            summary = "Create new rabbit",
            description = "Endpoint to create a new rabbit record"
    )
    public ResponseEntity<RabbitDTOResponse> createRabbit(
            @Valid @RequestBody RabbitDTORequest rabbit
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(rabbitService.createRabbit(rabbit));
    }

    @PutMapping("/update/{rabbitId}")
    @Operation(
            summary = "Update all rabbit data",
            description = "Endpoint to update the rabbit record"
    )
    public ResponseEntity<RabbitDTOResponse> updateRabbit(
            @PathVariable("rabbitId") Integer rabbitId,
            @Valid @RequestBody RabbitDTORequest rabbitDTORequest
    ) {
        return ResponseEntity.ok(rabbitService.updateRabbit(rabbitId, rabbitDTORequest));
    }
}