package com.senac.bunnyhut.controller;

import com.senac.bunnyhut.dto.request.ItemDTORequest;
import com.senac.bunnyhut.dto.response.ItemDTOResponse;
import com.senac.bunnyhut.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/item")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/list")
    @Operation(
            summary = "List all items",
            description = "Endpoint to list all items"
    )
    public ResponseEntity<List<ItemDTOResponse>> listItems() {
        return ResponseEntity.ok(itemService.listItems());
    }

    @GetMapping("/listById/{itemId}")
    @Operation(
            summary = "List item by ID",
            description = "Endpoint to list item by ID"
    )
    public ResponseEntity<ItemDTOResponse> getItemById(@PathVariable("itemId") Integer itemId) {
        ItemDTOResponse item = itemService.getItemById(itemId);
        if (item == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(item);
        }
    }
    @PostMapping("/create")
    @Operation(
            summary = "Create new item",
            description = "Endpoint to create a new item record"
    )
    public ResponseEntity<ItemDTOResponse> createItem(
            @Valid @RequestBody ItemDTORequest item
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(itemService.createItem(item));
    }

    @PutMapping("/update/{itemId}")
    @Operation(
            summary = "Update all item data",
            description = "Endpoint to update the item record"
    )
    public ResponseEntity<ItemDTOResponse> updateItem(
            @PathVariable("itemId") Integer itemId,
            @Valid @RequestBody ItemDTORequest itemDTORequest
    ) {
        return ResponseEntity.ok(itemService.updateItem(itemId, itemDTORequest));
    }

    @DeleteMapping("/delete/{itemId}")
    @Operation(
            summary = "Delete item record (logical deletion)",
            description = "Endpoint to logically delete item record"
    )
    public ResponseEntity<Void> deleteItem(@PathVariable("itemId") Integer itemId) {
        itemService.deleteItem(itemId);
        return ResponseEntity.noContent().build();
    }
}