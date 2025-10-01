package com.senac.bunnyhut.controller;

import com.senac.bunnyhut.dto.request.ItemDTORequest;
import com.senac.bunnyhut.dto.response.ItemDTOResponse;
import com.senac.bunnyhut.dto.response.ItemDTOUpdateResponse;
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

    @GetMapping("/listar")
    @Operation(
            summary = "Listar items",
            description = "Endpoint para listar todos os items"
    )
    public ResponseEntity<List<ItemDTOResponse>> listItems() {
        return ResponseEntity.ok(itemService.listItems());
    }

    @GetMapping("/listarPorItemId/{itemId}")
    @Operation(
            summary = "Listar item pelo id de item",
            description = "Endpoint para listar item por Id de item"
    )
    public ResponseEntity<ItemDTOResponse> listarPorItemId(@PathVariable("itemId") Integer itemId) {
        ItemDTOResponse item = itemService.listarPorItemId(itemId);
        if (item == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(item);
        }
    }
    @PostMapping("/criar")
    @Operation(
            summary = "Criar novo item",
            description = "Endpoint para criar um novo registro de item"
    )
    public ResponseEntity<ItemDTOResponse> criarItem(
            @Valid @RequestBody ItemDTORequest item
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(itemService.criarItem(item));
    }

    @PutMapping("/atualizar/{itemId}")
    @Operation(
            summary = "Atualizar todos os dados do item",
            description = "Endpoint para atualizar o registro de item"
    )
    public ResponseEntity<ItemDTOResponse> atualizarItem(
            @PathVariable("itemId") Integer itemId,
            @Valid @RequestBody ItemDTORequest itemDTORequest
    ) {
        return ResponseEntity.ok(itemService.atualizarItem(itemId, itemDTORequest));
    }

//    @PatchMapping("/atualizarStatus/{itemId}")
//    @Operation(
//            summary = "Atualizar campo status do item",
//            description = "Endpoint para atualizar apenas o status do item"
//    )
//    public ResponseEntity<ItemDTOUpdateResponse> atualizarStatusItem(
//            @PathVariable("itemId") Integer itemId,
//            @Valid @RequestBody ItemDTORequest itemDTOUpdateRequest
//    ) {
//        return ResponseEntity.ok(itemService.atualizarStatusItem(itemId, itemDTOUpdateRequest));
//    }

    @DeleteMapping("/apagar/{itemId}")
    @Operation(
            summary = "Apagar registro do item",
            description = "Endpoint para apagar registro do item"
    )
    public ResponseEntity<Void> apagarItem(@PathVariable("itemId") Integer itemId) {
        itemService.apagarItem(itemId);
        return ResponseEntity.noContent().build();
    }
}
