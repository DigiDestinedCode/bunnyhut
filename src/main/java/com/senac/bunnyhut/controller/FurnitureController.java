package com.senac.bunnyhut.controller;

import com.senac.bunnyhut.dto.request.FurnitureDTORequest;
import com.senac.bunnyhut.dto.response.FurnitureDTOResponse;
import com.senac.bunnyhut.service.FurnitureService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/furniture")
public class FurnitureController {

    private final FurnitureService furnitureService;

    public FurnitureController(FurnitureService furnitureService) {
        this.furnitureService = furnitureService;
    }

    @GetMapping("/list")
    @Operation(
            summary = "List all furnitures",
            description = "Endpoint to list all furnitures"
    )
    public ResponseEntity<List<FurnitureDTOResponse>> listFurnitures() {
        return ResponseEntity.ok(furnitureService.listFurnitures());
    }

    @GetMapping("/listById/{furnitureId}")
    @Operation(
            summary = "List furniture by ID",
            description = "Endpoint to list furniture by ID"
    )
    public ResponseEntity<FurnitureDTOResponse> getFurnitureById(@PathVariable("furnitureId") Integer furnitureId) {
        FurnitureDTOResponse furniture = furnitureService.getFurnitureById(furnitureId);
        if (furniture == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(furniture);
        }
    }
    @PostMapping("/create")
    @Operation(
            summary = "Create new furniture",
            description = "Endpoint to create a new furniture record"
    )
    public ResponseEntity<FurnitureDTOResponse> createFurniture(
            @Valid @RequestBody FurnitureDTORequest furniture
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(furnitureService.createFurniture(furniture));
    }

    @PutMapping("/update/{furnitureId}")
    @Operation(
            summary = "Update all furniture data",
            description = "Endpoint to update the furniture record"
    )
    public ResponseEntity<FurnitureDTOResponse> updateFurniture(
            @PathVariable("furnitureId") Integer furnitureId,
            @Valid @RequestBody FurnitureDTORequest furnitureDTORequest
    ) {
        return ResponseEntity.ok(furnitureService.updateFurniture(furnitureId, furnitureDTORequest));
    }
}