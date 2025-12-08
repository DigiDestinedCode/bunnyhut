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

    @GetMapping("/listar")
    @Operation(
            summary = "Listar gardenspots",
            description = "Endpoint para listar todos os gardenspots"
    )
    public ResponseEntity<List<GardenSpotDTOResponse>> listGardenspots() {
        return ResponseEntity.ok(gardenspotService.listGardenSpots());
    }

    @GetMapping("/listarPorGardenspotId/{gardenspotId}")
    @Operation(
            summary = "Listar gardenspot pelo id de gardenspot",
            description = "Endpoint para listar gardenspot por Id de gardenspot"
    )
    public ResponseEntity<GardenSpotDTOResponse> listarPorGardenspotId(@PathVariable("gardenspotId") Integer gardenspotId) {
        GardenSpotDTOResponse gardenspot = gardenspotService.listarPorGardenSpotId(gardenspotId);
        if (gardenspot == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(gardenspot);
        }
    }
    @PostMapping("/criar")
    @Operation(
            summary = "Criar novo gardenspot",
            description = "Endpoint para criar um novo registro de gardenspot"
    )
    public ResponseEntity<GardenSpotDTOResponse> criarGardenspot(
            @Valid @RequestBody GardenSpotDTORequest gardenspot
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(gardenspotService.criarGardenSpot(gardenspot));
    }

    @PutMapping("/atualizar/{gardenspotId}")
    @Operation(
            summary = "Atualizar todos os dados do gardenspot",
            description = "Endpoint para atualizar o registro de gardenspot"
    )
    public ResponseEntity<GardenSpotDTOResponse> atualizarGardenspot(
            @PathVariable("gardenspotId") Integer gardenspotId,
            @Valid @RequestBody GardenSpotDTORequest gardenspotDTORequest
    ) {
        return ResponseEntity.ok(gardenspotService.atualizarGardenSpot(gardenspotId, gardenspotDTORequest));
    }
}
