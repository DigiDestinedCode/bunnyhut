package com.senac.bunnyhut.controller;

import com.senac.bunnyhut.dto.request.BackgroundSlotDTORequest;
import com.senac.bunnyhut.dto.response.BackgroundSlotDTOResponse;
import com.senac.bunnyhut.service.BackgroundSlotService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/backgroundslot")
public class BackgroundSlotController {

    private final BackgroundSlotService backgroundslotService;

    public BackgroundSlotController(BackgroundSlotService backgroundslotService) {
        this.backgroundslotService = backgroundslotService;
    }

    @GetMapping("/list")
    @Operation(
            summary = "List all background slots",
            description = "Endpoint to list all background slots"
    )
    public ResponseEntity<List<BackgroundSlotDTOResponse>> listBackgroundSlots() {
        return ResponseEntity.ok(backgroundslotService.listBackgroundSlots());
    }

    @GetMapping("/listById/{backgroundSlotId}")
    @Operation(
            summary = "List background slot by ID",
            description = "Endpoint to list background slot by ID"
    )
    public ResponseEntity<BackgroundSlotDTOResponse> getBackgroundSlotById(@PathVariable("backgroundSlotId") Integer backgroundSlotId) {
        BackgroundSlotDTOResponse backgroundSlot = backgroundslotService.getBackgroundSlotById(backgroundSlotId);
        if (backgroundSlot == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(backgroundSlot);
        }
    }
    @PostMapping("/create")
    @Operation(
            summary = "Create new background slot",
            description = "Endpoint to create a new background slot record"
    )
    public ResponseEntity<BackgroundSlotDTOResponse> createBackgroundSlot(
            @Valid @RequestBody BackgroundSlotDTORequest backgroundSlot
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(backgroundslotService.createBackgroundSlot(backgroundSlot));
    }

    @PutMapping("/update/{backgroundSlotId}")
    @Operation(
            summary = "Update all background slot data",
            description = "Endpoint to update the background slot record"
    )
    public ResponseEntity<BackgroundSlotDTOResponse> updateBackgroundSlot(
            @PathVariable("backgroundSlotId") Integer backgroundSlotId,
            @Valid @RequestBody BackgroundSlotDTORequest backgroundSlotDTORequest
    ) {
        return ResponseEntity.ok(backgroundslotService.updateBackgroundSlot(backgroundSlotId, backgroundSlotDTORequest));
    }
}