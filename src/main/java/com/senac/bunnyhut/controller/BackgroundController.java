package com.senac.bunnyhut.controller;

import com.senac.bunnyhut.dto.request.BackgroundDTORequest;
import com.senac.bunnyhut.dto.response.BackgroundDTOResponse;
import com.senac.bunnyhut.service.BackgroundService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/background")
public class BackgroundController {

    private final BackgroundService backgroundService;

    public BackgroundController(BackgroundService backgroundService) {
        this.backgroundService = backgroundService;
    }

    @GetMapping("/list")
    @Operation(
            summary = "List all backgrounds",
            description = "Endpoint to list all backgrounds"
    )
    public ResponseEntity<List<BackgroundDTOResponse>> listBackgrounds() {
        return ResponseEntity.ok(backgroundService.listBackgrounds());
    }

    @GetMapping("/listById/{backgroundId}")
    @Operation(
            summary = "List background by ID",
            description = "Endpoint to list background by ID"
    )
    public ResponseEntity<BackgroundDTOResponse> getBackgroundById(@PathVariable("backgroundId") Integer backgroundId) {
        BackgroundDTOResponse background = backgroundService.getBackgroundById(backgroundId);
        if (background == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(background);
        }
    }
    @PostMapping("/create")
    @Operation(
            summary = "Create new background",
            description = "Endpoint to create a new background record"
    )
    public ResponseEntity<BackgroundDTOResponse> createBackground(
            @Valid @RequestBody BackgroundDTORequest background
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(backgroundService.createBackground(background));
    }

    @PutMapping("/update/{backgroundId}")
    @Operation(
            summary = "Update all background data",
            description = "Endpoint to update the background record"
    )
    public ResponseEntity<BackgroundDTOResponse> updateBackground(
            @PathVariable("backgroundId") Integer backgroundId,
            @Valid @RequestBody BackgroundDTORequest backgroundDTORequest
    ) {
        return ResponseEntity.ok(backgroundService.updateBackground(backgroundId, backgroundDTORequest));
    }
}