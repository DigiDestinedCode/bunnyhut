package com.senac.bunnyhut.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/listar")
    @Operation(
            summary = "Listar inventorys",
            description = "Endpoint para listar todos os inventorys"
    )
    public ResponseEntity<List<InventoryDTOResponse>> listarInventorys() {
        return ResponseEntity.ok(inventoryService.listarInventorys());
    }

    @GetMapping("/listarPorInventoryId/{inventoryId}")
    @Operation(
            summary = "Listar inventory pelo id de inventory",
            description = "Endpoint para listar inventory por Id de inventory"
    )
    public ResponseEntity<InventoryDTOResponse> listarPorInventoryId(@PathVariable("inventoryId") Integer inventoryId) {
        InventoryDTOResponse inventory = inventoryService.listarPorInventoryId(inventoryId);
        if (inventory == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(inventory);
        }
    }
    @PostMapping("/criar")
    @Operation(
            summary = "Criar novo inventory",
            description = "Endpoint para criar um novo registro de inventory"
    )
    public ResponseEntity<InventoryDTOResponse> criarInventory(
            @Valid @RequestBody InventoryDTORequest inventory
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(inventoryService.criarInventory(inventory));
    }

    @PutMapping("/atualizar/{inventoryId}")
    @Operation(
            summary = "Atualizar todos os dados do inventory",
            description = "Endpoint para atualizar o registro de inventory"
    )
    public ResponseEntity<InventoryDTOResponse> atualizarInventory(
            @PathVariable("inventoryId") Integer inventoryId,
            @Valid @RequestBody InventoryDTORequest inventoryDTORequest
    ) {
        return ResponseEntity.ok(inventoryService.atualizarInventory(inventoryId, inventoryDTORequest));
    }

    @PatchMapping("/atualizarStatus/{inventoryId}")
    @Operation(
            summary = "Atualizar campo status do inventory",
            description = "Endpoint para atualizar apenas o status do inventory"
    )
    public ResponseEntity<InventoryDTOUpdateResponse> atualizarStatusInventory(
            @PathVariable("inventoryId") Integer inventoryId,
            @Valid @RequestBody InventoryDTORequest inventoryDTOUpdateRequest
    ) {
        return ResponseEntity.ok(inventoryService.atualizarStatusInventory(inventoryId, inventoryDTOUpdateRequest));
    }

    @DeleteMapping("/apagar/{inventoryId}")
    @Operation(
            summary = "Apagar registro do inventory",
            description = "Endpoint para apagar registro do inventory"
    )
    public ResponseEntity<Void> apagarInventory(@PathVariable("inventoryId") Integer inventoryId) {
        inventoryService.apagarInventory(inventoryId);
        return ResponseEntity.noContent().build();
    }
}
