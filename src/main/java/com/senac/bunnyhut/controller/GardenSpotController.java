package com.senac.bunnyhut.controller;

import com.senac.bunnyhut.dto.request.GardenSpotDTORequest;
import com.senac.bunnyhut.dto.response.GardenSpotDTOResponse;
import com.senac.bunnyhut.service.GardenSpotService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/gardenspot")
public class GardenSpotController {

    private final GardenSpotService gardenspotService;

    public GardenSpotController(GardenSpotService gardenspotService) {
        this.gardenspotService = gardenspotService;
    }

    @GetMapping("/list")
    @Operation(
            summary = "List all garden spots",
            description = "Endpoint to list all garden spots"
    )
    public ResponseEntity<List<GardenSpotDTOResponse>> listGardenSpots() {
        return ResponseEntity.ok(gardenspotService.listGardenSpots());
    }

    @GetMapping("/listById/{gardenspotId}")
    @Operation(
            summary = "List garden spot by ID",
            description = "Endpoint to list garden spot by ID"
    )
    public ResponseEntity<GardenSpotDTOResponse> getGardenSpotById(@PathVariable("gardenspotId") Integer gardenspotId) {
        GardenSpotDTOResponse gardenspot = gardenspotService.getGardenSpotById(gardenspotId);
        if (gardenspot == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(gardenspot);
        }
    }
    @PostMapping("/create")
    @Operation(
            summary = "Create new garden spot",
            description = "Endpoint to create a new garden spot record"
    )
    public ResponseEntity<GardenSpotDTOResponse> createGardenSpot(
            @Valid @RequestBody GardenSpotDTORequest gardenspot
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(gardenspotService.createGardenSpot(gardenspot));
    }

    @PutMapping("/update/{gardenspotId}")
    @Operation(
            summary = "Update all garden spot data",
            description = "Endpoint to update the garden spot record"
    )
    public ResponseEntity<GardenSpotDTOResponse> updateGardenSpot(
            @PathVariable("gardenspotId") Integer gardenspotId,
            @Valid @RequestBody GardenSpotDTORequest gardenspotDTORequest
    ) {
        return ResponseEntity.ok(gardenspotService.updateGardenSpot(gardenspotId, gardenspotDTORequest));
    }
}