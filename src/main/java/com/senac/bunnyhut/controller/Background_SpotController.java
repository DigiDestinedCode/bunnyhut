package com.senac.bunnyhut.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class Background_SpotController {

    private final Background_SpotService background_spotService;

    public Background_SpotController(Background_SpotService background_spotService) {
        this.background_spotService = background_spotService;
    }

    @GetMapping("/listar")
    @Operation(
            summary = "Listar background_spots",
            description = "Endpoint para listar todos os background_spots"
    )
    public ResponseEntity<List<Background_SpotDTOResponse>> listarBackground_Spots() {
        return ResponseEntity.ok(background_spotService.listarBackground_Spots());
    }

    @GetMapping("/listarPorBackground_SpotId/{background_spotId}")
    @Operation(
            summary = "Listar background_spot pelo id de background_spot",
            description = "Endpoint para listar background_spot por Id de background_spot"
    )
    public ResponseEntity<Background_SpotDTOResponse> listarPorBackground_SpotId(@PathVariable("background_spotId") Integer background_spotId) {
        Background_SpotDTOResponse background_spot = background_spotService.listarPorBackground_SpotId(background_spotId);
        if (background_spot == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(background_spot);
        }
    }
    @PostMapping("/criar")
    @Operation(
            summary = "Criar novo background_spot",
            description = "Endpoint para criar um novo registro de background_spot"
    )
    public ResponseEntity<Background_SpotDTOResponse> criarBackground_Spot(
            @Valid @RequestBody Background_SpotDTORequest background_spot
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(background_spotService.criarBackground_Spot(background_spot));
    }

    @PutMapping("/atualizar/{background_spotId}")
    @Operation(
            summary = "Atualizar todos os dados do background_spot",
            description = "Endpoint para atualizar o registro de background_spot"
    )
    public ResponseEntity<Background_SpotDTOResponse> atualizarBackground_Spot(
            @PathVariable("background_spotId") Integer background_spotId,
            @Valid @RequestBody Background_SpotDTORequest background_spotDTORequest
    ) {
        return ResponseEntity.ok(background_spotService.atualizarBackground_Spot(background_spotId, background_spotDTORequest));
    }

    @PatchMapping("/atualizarStatus/{background_spotId}")
    @Operation(
            summary = "Atualizar campo status do background_spot",
            description = "Endpoint para atualizar apenas o status do background_spot"
    )
    public ResponseEntity<Background_SpotDTOUpdateResponse> atualizarStatusBackground_Spot(
            @PathVariable("background_spotId") Integer background_spotId,
            @Valid @RequestBody Background_SpotDTORequest background_spotDTOUpdateRequest
    ) {
        return ResponseEntity.ok(background_spotService.atualizarStatusBackground_Spot(background_spotId, background_spotDTOUpdateRequest));
    }

    @DeleteMapping("/apagar/{background_spotId}")
    @Operation(
            summary = "Apagar registro do background_spot",
            description = "Endpoint para apagar registro do background_spot"
    )
    public ResponseEntity<Void> apagarBackground_Spot(@PathVariable("background_spotId") Integer background_spotId) {
        background_spotService.apagarBackground_Spot(background_spotId);
        return ResponseEntity.noContent().build();
    }
}
