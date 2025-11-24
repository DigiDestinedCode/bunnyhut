package com.senac.bunnyhut.controller;

import com.senac.bunnyhut.dto.request.Garden_SpotDTORequest;
import com.senac.bunnyhut.dto.response.Garden_SpotDTOResponse;
import com.senac.bunnyhut.dto.response.Garden_SpotDTOUpdateResponse;
import com.senac.bunnyhut.service.Garden_SpotService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/garden-spot")
public class Garden_SpotController {

    private final Garden_SpotService garden_spotService;

    public Garden_SpotController(Garden_SpotService garden_spotService) {
        this.garden_spotService = garden_spotService;
    }

    @GetMapping("/listar")
    @Operation(
            summary = "Listar garden_spots",
            description = "Endpoint para listar todos os garden_spots"
    )
    public ResponseEntity<List<Garden_SpotDTOResponse>> listGarden_Spots() {
        return ResponseEntity.ok(garden_spotService.listGarden_Spots());
    }

    @GetMapping("/listarPorGarden_SpotId/{garden_spotId}")
    @Operation(
            summary = "Listar garden_spot pelo id de garden_spot",
            description = "Endpoint para listar garden_spot por Id de garden_spot"
    )
    public ResponseEntity<Garden_SpotDTOResponse> listarPorGarden_SpotId(@PathVariable("garden_spotId") Integer garden_spotId) {
        Garden_SpotDTOResponse garden_spot = garden_spotService.listarPorGarden_SpotId(garden_spotId);
        if (garden_spot == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(garden_spot);
        }
    }
    @PostMapping("/criar")
    @Operation(
            summary = "Criar novo garden_spot",
            description = "Endpoint para criar um novo registro de garden_spot"
    )
    public ResponseEntity<Garden_SpotDTOResponse> criarGarden_Spot(
            @Valid @RequestBody Garden_SpotDTORequest garden_spot
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(garden_spotService.criarGarden_Spot(garden_spot));
    }

    @PutMapping("/atualizar/{garden_spotId}")
    @Operation(
            summary = "Atualizar todos os dados do garden_spot",
            description = "Endpoint para atualizar o registro de garden_spot"
    )
    public ResponseEntity<Garden_SpotDTOResponse> atualizarGarden_Spot(
            @PathVariable("garden_spotId") Integer garden_spotId,
            @Valid @RequestBody Garden_SpotDTORequest garden_spotDTORequest
    ) {
        return ResponseEntity.ok(garden_spotService.atualizarGarden_Spot(garden_spotId, garden_spotDTORequest));
    }
}
