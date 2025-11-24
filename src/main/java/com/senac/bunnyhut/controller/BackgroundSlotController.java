package com.senac.bunnyhut.controller;

import com.senac.bunnyhut.dto.request.BackgroundSlotDTORequest;
import com.senac.bunnyhut.dto.response.BackgroundSlotDTOResponse;
import com.senac.bunnyhut.service.BackgroundSlotService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/background-slot")
public class Background_SlotController {

    private final BackgroundSlotService background_slotService;

    public Background_SlotController(BackgroundSlotService background_slotService) {
        this.background_slotService = background_slotService;
    }

    @GetMapping("/listar")
    @Operation(
            summary = "Listar background_slots",
            description = "Endpoint para listar todos os background_slots"
    )
    public ResponseEntity<List<BackgroundSlotDTOResponse>> listBackground_Slots() {
        return ResponseEntity.ok(background_slotService.listBackground_Slots());
    }

    @GetMapping("/listarPorBackground_SlotId/{background_slotId}")
    @Operation(
            summary = "Listar background_slot pelo id de background_slot",
            description = "Endpoint para listar background_slot por Id de background_slot"
    )
    public ResponseEntity<BackgroundSlotDTOResponse> listarPorBackground_SlotId(@PathVariable("background_slotId") Integer background_slotId) {
        BackgroundSlotDTOResponse background_slot = background_slotService.listarPorBackground_SlotId(background_slotId);
        if (background_slot == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(background_slot);
        }
    }
    @PostMapping("/criar")
    @Operation(
            summary = "Criar novo background_slot",
            description = "Endpoint para criar um novo registro de background_slot"
    )
    public ResponseEntity<BackgroundSlotDTOResponse> criarBackground_Slot(
            @Valid @RequestBody BackgroundSlotDTORequest background_slot
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(background_slotService.criarBackground_Slot(background_slot));
    }

    @PutMapping("/atualizar/{background_slotId}")
    @Operation(
            summary = "Atualizar todos os dados do background_slot",
            description = "Endpoint para atualizar o registro de background_slot"
    )
    public ResponseEntity<BackgroundSlotDTOResponse> atualizarBackground_Slot(
            @PathVariable("background_slotId") Integer background_slotId,
            @Valid @RequestBody BackgroundSlotDTORequest background_slotDTORequest
    ) {
        return ResponseEntity.ok(background_slotService.atualizarBackground_Slot(background_slotId, background_slotDTORequest));
    }
}
