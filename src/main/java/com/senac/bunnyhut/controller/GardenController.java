package com.senac.bunnyhut.controller;

import com.senac.bunnyhut.dto.request.GardenDTORequest;
import com.senac.bunnyhut.dto.response.GardenDTOResponse;
import com.senac.bunnyhut.service.GardenService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/garden")
public class GardenController {

    private final GardenService gardenService;

    public GardenController(GardenService gardenService) {
        this.gardenService = gardenService;
    }

    @GetMapping("/list")
    @Operation(
            summary = "List all gardens",
            description = "Endpoint to list all gardens"
    )
    public ResponseEntity<List<GardenDTOResponse>> listGardens() {
        return ResponseEntity.ok(gardenService.listGardens());
    }

    @GetMapping("/listById/{gardenId}")
    @Operation(
            summary = "List garden by ID",
            description = "Endpoint to list garden by ID"
    )
    public ResponseEntity<GardenDTOResponse> getGardenById(@PathVariable("gardenId") Integer gardenId) {
        GardenDTOResponse garden = gardenService.getGardenById(gardenId);
        if (garden == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(garden);
        }
    }
    @PostMapping("/create")
    @Operation(
            summary = "Create new garden",
            description = "Endpoint to create a new garden record"
    )
    public ResponseEntity<GardenDTOResponse> createGarden(
            @Valid @RequestBody GardenDTORequest garden
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(gardenService.createGarden(garden));
    }

    @PutMapping("/update/{gardenId}")
    @Operation(
            summary = "Update all garden data",
            description = "Endpoint to update the garden record"
    )
    public ResponseEntity<GardenDTOResponse> updateGarden(
            @PathVariable("gardenId") Integer gardenId,
            @Valid @RequestBody GardenDTORequest gardenDTORequest
    ) {
        return ResponseEntity.ok(gardenService.updateGarden(gardenId, gardenDTORequest));
    }
}