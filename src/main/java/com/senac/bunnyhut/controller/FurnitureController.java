package com.senac.bunnyhut.controller;

import com.senac.bunnyhut.dto.request.FurnitureDTORequest;
import com.senac.bunnyhut.dto.response.FurnitureDTOResponse;
import com.senac.bunnyhut.dto.response.FurnitureDTOUpdateResponse;
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

    @GetMapping("/listar")
    @Operation(
            summary = "Listar furnitures",
            description = "Endpoint para listar todos os furnitures"
    )
    public ResponseEntity<List<FurnitureDTOResponse>> listFurnitures() {
        return ResponseEntity.ok(furnitureService.listFurnitures());
    }

    @GetMapping("/listarPorFurnitureId/{furnitureId}")
    @Operation(
            summary = "Listar furniture pelo id de furniture",
            description = "Endpoint para listar furniture por Id de furniture"
    )
    public ResponseEntity<FurnitureDTOResponse> listarPorFurnitureId(@PathVariable("furnitureId") Integer furnitureId) {
        FurnitureDTOResponse furniture = furnitureService.listarPorFurnitureId(furnitureId);
        if (furniture == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(furniture);
        }
    }
    @PostMapping("/criar")
    @Operation(
            summary = "Criar novo furniture",
            description = "Endpoint para criar um novo registro de furniture"
    )
    public ResponseEntity<FurnitureDTOResponse> criarFurniture(
            @Valid @RequestBody FurnitureDTORequest furniture
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(furnitureService.criarFurniture(furniture));
    }

    @PutMapping("/atualizar/{furnitureId}")
    @Operation(
            summary = "Atualizar todos os dados do furniture",
            description = "Endpoint para atualizar o registro de furniture"
    )
    public ResponseEntity<FurnitureDTOResponse> atualizarFurniture(
            @PathVariable("furnitureId") Integer furnitureId,
            @Valid @RequestBody FurnitureDTORequest furnitureDTORequest
    ) {
        return ResponseEntity.ok(furnitureService.atualizarFurniture(furnitureId, furnitureDTORequest));
    }

//    @PatchMapping("/atualizarStatus/{furnitureId}")
//    @Operation(
//            summary = "Atualizar campo status do furniture",
//            description = "Endpoint para atualizar apenas o status do furniture"
//    )
//    public ResponseEntity<FurnitureDTOUpdateResponse> atualizarStatusFurniture(
//            @PathVariable("furnitureId") Integer furnitureId,
//            @Valid @RequestBody FurnitureDTORequest furnitureDTOUpdateRequest
//    ) {
//        return ResponseEntity.ok(furnitureService.atualizarStatusFurniture(furnitureId, furnitureDTOUpdateRequest));
//    }

//    @DeleteMapping("/apagar/{furnitureId}")
//    @Operation(
//            summary = "Apagar registro do furniture",
//            description = "Endpoint para apagar registro do furniture"
//    )
//    public ResponseEntity<Void> apagarFurniture(@PathVariable("furnitureId") Integer furnitureId) {
//        furnitureService.apagarFurniture(furnitureId);
//        return ResponseEntity.noContent().build();
//    }
}
