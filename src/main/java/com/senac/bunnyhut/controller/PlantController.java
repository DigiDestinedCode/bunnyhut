package com.senac.bunnyhut.controller;

import com.senac.bunnyhut.dto.request.PlantDTORequest;
import com.senac.bunnyhut.dto.response.PlantDTOResponse;
import com.senac.bunnyhut.service.PlantService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/plant")
public class PlantController {

    private final PlantService plantService;

    public PlantController(PlantService plantService) {
        this.plantService = plantService;
    }

    @GetMapping("/list")
    @Operation(
            summary = "List all plants",
            description = "Endpoint to list all plants"
    )
    public ResponseEntity<List<PlantDTOResponse>> listPlants() {
        return ResponseEntity.ok(plantService.listPlants());
    }

    @GetMapping("/listById/{plantId}")
    @Operation(
            summary = "List plant by ID",
            description = "Endpoint to list plant by ID"
    )
    public ResponseEntity<PlantDTOResponse> getPlantById(@PathVariable("plantId") Integer plantId) {
        PlantDTOResponse plant = plantService.getPlantById(plantId);
        if (plant == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(plant);
        }
    }
    @PostMapping("/create")
    @Operation(
            summary = "Create new plant",
            description = "Endpoint to create a new plant record"
    )
    public ResponseEntity<PlantDTOResponse> createPlant(
            @Valid @RequestBody PlantDTORequest plant
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(plantService.createPlant(plant));
    }

    @PutMapping("/update/{plantId}")
    @Operation(
            summary = "Update all plant data",
            description = "Endpoint to update the plant record"
    )
    public ResponseEntity<PlantDTOResponse> updatePlant(
            @PathVariable("plantId") Integer plantId,
            @Valid @RequestBody PlantDTORequest plantDTORequest
    ) {
        return ResponseEntity.ok(plantService.updatePlant(plantId, plantDTORequest));
    }
}