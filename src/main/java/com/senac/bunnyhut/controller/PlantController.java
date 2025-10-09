package com.senac.bunnyhut.controller;

import com.senac.bunnyhut.dto.request.PlantDTORequest;
import com.senac.bunnyhut.dto.response.PlantDTOResponse;
import com.senac.bunnyhut.dto.response.PlantDTOUpdateResponse;
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

    @GetMapping("/listar")
    @Operation(
            summary = "Listar plants",
            description = "Endpoint para listar todos os plants"
    )
    public ResponseEntity<List<PlantDTOResponse>> listPlants() {
        return ResponseEntity.ok(plantService.listPlants());
    }

    @GetMapping("/listarPorPlantId/{plantId}")
    @Operation(
            summary = "Listar plant pelo id de plant",
            description = "Endpoint para listar plant por Id de plant"
    )
    public ResponseEntity<PlantDTOResponse> listarPorPlantId(@PathVariable("plantId") Integer plantId) {
        PlantDTOResponse plant = plantService.listarPorPlantId(plantId);
        if (plant == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(plant);
        }
    }
    @PostMapping("/criar")
    @Operation(
            summary = "Criar novo plant",
            description = "Endpoint para criar um novo registro de plant"
    )
    public ResponseEntity<PlantDTOResponse> criarPlant(
            @Valid @RequestBody PlantDTORequest plant
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(plantService.criarPlant(plant));
    }

    @PutMapping("/atualizar/{plantId}")
    @Operation(
            summary = "Atualizar todos os dados do plant",
            description = "Endpoint para atualizar o registro de plant"
    )
    public ResponseEntity<PlantDTOResponse> atualizarPlant(
            @PathVariable("plantId") Integer plantId,
            @Valid @RequestBody PlantDTORequest plantDTORequest
    ) {
        return ResponseEntity.ok(plantService.atualizarPlant(plantId, plantDTORequest));
    }
}
