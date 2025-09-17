package com.senac.bunnyhut.controller;

import com.senac.bunnyhut.dto.request.Background_SlotDTORequest;
import com.senac.bunnyhut.dto.response.Background_SlotDTOResponse;
import com.senac.bunnyhut.dto.response.Background_SlotDTOUpdateResponse;
import com.senac.bunnyhut.service.Background_SlotService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class Background_SlotController {

    private final Background_SlotService background_SlotService;

    public Background_SlotController(Background_SlotService background_SlotService) {
        this.background_SlotService = background_SlotService;
    }

    @GetMapping("/listar")
    @Operation(
            summary = "Listar background_Slots",
            description = "Endpoint para listar todos os background_Slots"
    )
    public ResponseEntity<List<Background_SlotDTOResponse>> listBackground_Slots() {
        return ResponseEntity.ok(background_SlotService.listBackground_Slots());
    }

    @GetMapping("/listarPorBackground_SlotId/{background_SlotId}")
    @Operation(
            summary = "Listar background_Slot pelo id de background_Slot",
            description = "Endpoint para listar background_Slot por Id de background_Slot"
    )
    public ResponseEntity<Background_SlotDTOResponse> listarPorBackground_SlotId(@PathVariable("background_SlotId") Integer background_SlotId) {
        Background_SlotDTOResponse background_Slot = background_SlotService.listarPorBackground_SlotId(background_SlotId);
        if (background_Slot == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(background_Slot);
        }
    }
    @PostMapping("/criar")
    @Operation(
            summary = "Criar novo background_Slot",
            description = "Endpoint para criar um novo registro de background_Slot"
    )
    public ResponseEntity<Background_SlotDTOResponse> criarBackground_Slot(
            @Valid @RequestBody Background_SlotDTORequest background_Slot
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(background_SlotService.criarBackground_Slot(background_Slot));
    }

    @PutMapping("/atualizar/{background_SlotId}")
    @Operation(
            summary = "Atualizar todos os dados do background_Slot",
            description = "Endpoint para atualizar o registro de background_Slot"
    )
    public ResponseEntity<Background_SlotDTOResponse> atualizarBackground_Slot(
            @PathVariable("background_SlotId") Integer background_SlotId,
            @Valid @RequestBody Background_SlotDTORequest background_SlotDTORequest
    ) {
        return ResponseEntity.ok(background_SlotService.atualizarBackground_Slot(background_SlotId, background_SlotDTORequest));
    }

    @PatchMapping("/atualizarStatus/{background_SlotId}")
    @Operation(
            summary = "Atualizar campo status do background_Slot",
            description = "Endpoint para atualizar apenas o status do background_Slot"
    )
    public ResponseEntity<Background_SlotDTOUpdateResponse> atualizarStatusBackground_Slot(
            @PathVariable("background_SlotId") Integer background_SlotId,
            @Valid @RequestBody Background_SlotDTORequest background_SlotDTOUpdateRequest
    ) {
        return ResponseEntity.ok(background_SlotService.atualizarStatusBackground_Slot(background_SlotId, background_SlotDTOUpdateRequest));
    }

    @DeleteMapping("/apagar/{background_SlotId}")
    @Operation(
            summary = "Apagar registro do background_Slot",
            description = "Endpoint para apagar registro do background_Slot"
    )
    public ResponseEntity<Void> apagarBackground_Slot(@PathVariable("background_SlotId") Integer background_SlotId) {
        background_SlotService.apagarBackground_Slot(background_SlotId);
        return ResponseEntity.noContent().build();
    }
}
