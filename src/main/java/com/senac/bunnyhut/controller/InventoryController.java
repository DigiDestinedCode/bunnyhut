package com.senac.bunnyhut.controller;

import com.senac.bunnyhut.dto.request.InventoryDTORequest;
import com.senac.bunnyhut.dto.response.InventoryDTOResponse;
import com.senac.bunnyhut.service.InventoryService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/list")
    @Operation(
            summary = "List all inventories",
            description = "Endpoint to list all inventories"
    )
    public ResponseEntity<List<InventoryDTOResponse>> listInventories() {
        return ResponseEntity.ok(inventoryService.listInventories());
    }

    @GetMapping("/listById/{inventoryId}")
    @Operation(
            summary = "List inventory by ID",
            description = "Endpoint to list inventory by ID"
    )
    public ResponseEntity<InventoryDTOResponse> getInventoryById(@PathVariable("inventoryId") Integer inventoryId) {
        InventoryDTOResponse inventory = inventoryService.getInventoryById(inventoryId);
        if (inventory == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(inventory);
        }
    }
    @PostMapping("/create")
    @Operation(
            summary = "Create new inventory",
            description = "Endpoint to create a new inventory record"
    )
    public ResponseEntity<InventoryDTOResponse> createInventory(
            @Valid @RequestBody InventoryDTORequest inventory
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(inventoryService.createInventory(inventory));
    }

    @PutMapping("/update/{inventoryId}")
    @Operation(
            summary = "Update all inventory data",
            description = "Endpoint to update the inventory record"
    )
    public ResponseEntity<InventoryDTOResponse> updateInventory(
            @PathVariable("inventoryId") Integer inventoryId,
            @Valid @RequestBody InventoryDTORequest inventoryDTORequest
    ) {
        return ResponseEntity.ok(inventoryService.updateInventory(inventoryId, inventoryDTORequest));
    }
}