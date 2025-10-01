package com.senac.bunnyhut.controller;

import com.senac.bunnyhut.dto.request.GardenDTORequest;
import com.senac.bunnyhut.dto.response.GardenDTOResponse;
import com.senac.bunnyhut.dto.response.GardenDTOUpdateResponse;
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

    @GetMapping("/listar")
    @Operation(
            summary = "Listar gardens",
            description = "Endpoint para listar todos os gardens"
    )
    public ResponseEntity<List<GardenDTOResponse>> listarGardens() {
        return ResponseEntity.ok(gardenService.listGardens());
    }

    @GetMapping("/listarPorGardenId/{gardenId}")
    @Operation(
            summary = "Listar garden pelo id de garden",
            description = "Endpoint para listar garden por Id de garden"
    )
    public ResponseEntity<GardenDTOResponse> listarPorGardenId(@PathVariable("gardenId") Integer gardenId) {
        GardenDTOResponse garden = gardenService.listarPorGardenId(gardenId);
        if (garden == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(garden);
        }
    }
    @PostMapping("/criar")
    @Operation(
            summary = "Criar novo garden",
            description = "Endpoint para criar um novo registro de garden"
    )
    public ResponseEntity<GardenDTOResponse> criarGarden(
            @Valid @RequestBody GardenDTORequest garden
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(gardenService.criarGarden(garden));
    }

    @PutMapping("/atualizar/{gardenId}")
    @Operation(
            summary = "Atualizar todos os dados do garden",
            description = "Endpoint para atualizar o registro de garden"
    )
    public ResponseEntity<GardenDTOResponse> atualizarGarden(
            @PathVariable("gardenId") Integer gardenId,
            @Valid @RequestBody GardenDTORequest gardenDTORequest
    ) {
        return ResponseEntity.ok(gardenService.atualizarGarden(gardenId, gardenDTORequest));
    }

//    @PatchMapping("/atualizarStatus/{gardenId}")
//    @Operation(
//            summary = "Atualizar campo status do garden",
//            description = "Endpoint para atualizar apenas o status do garden"
//    )
//    public ResponseEntity<GardenDTOUpdateResponse> atualizarStatusGarden(
//            @PathVariable("gardenId") Integer gardenId,
//            @Valid @RequestBody GardenDTORequest gardenDTOUpdateRequest
//    ) {
//        return ResponseEntity.ok(gardenService.atualizarStatusGarden(gardenId, gardenDTOUpdateRequest));
//    }

//    @DeleteMapping("/apagar/{gardenId}")
//    @Operation(
//            summary = "Apagar registro do garden",
//            description = "Endpoint para apagar registro do garden"
//    )
//    public ResponseEntity<Void> apagarGarden(@PathVariable("gardenId") Integer gardenId) {
//        gardenService.apagarGarden(gardenId);
//        return ResponseEntity.noContent().build();
//    }
}
