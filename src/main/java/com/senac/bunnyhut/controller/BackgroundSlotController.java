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
@RequestMapping("api/backgroundslot")
public class BackgroundSlotController {

    private final BackgroundSlotService backgroundslotService;

    public BackgroundSlotController(BackgroundSlotService backgroundslotService) {
        this.backgroundslotService = backgroundslotService;
    }

    @GetMapping("/listar")
    @Operation(
            summary = "Listar backgroundSlots",
            description = "Endpoint para listar todos os backgroundSlots"
    )
    public ResponseEntity<List<BackgroundSlotDTOResponse>> listBackgroundSlots() {
        return ResponseEntity.ok(backgroundslotService.listBackgroundSlots());
    }

    @GetMapping("/obterBackgroundslotPeloId/{backgroundSlotId}")
    @Operation(
            summary = "Listar backgroundSlot pelo id de backgroundSlot",
            description = "Endpoint para listar backgroundSlot por Id de backgroundSlot"
    )
    public ResponseEntity<BackgroundSlotDTOResponse> obterBackgroundslotPeloId(@PathVariable("backgroundSlotId") Integer backgroundSlotId) {
        BackgroundSlotDTOResponse backgroundSlot = backgroundslotService.listarPorBackgroundSlotId(backgroundSlotId);
        if (backgroundSlot == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(backgroundSlot);
        }
    }
    @PostMapping("/criar")
    @Operation(
            summary = "Criar novo backgroundSlot",
            description = "Endpoint para criar um novo registro de backgroundSlot"
    )
    public ResponseEntity<BackgroundSlotDTOResponse> criarBackgroundSlot(
            @Valid @RequestBody BackgroundSlotDTORequest backgroundSlot
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(backgroundslotService.criarBackgroundSlot(backgroundSlot));
    }

    @PutMapping("/atualizar/{backgroundSlotId}")
    @Operation(
            summary = "Atualizar todos os dados do backgroundSlot",
            description = "Endpoint para atualizar o registro de backgroundSlot"
    )
    public ResponseEntity<BackgroundSlotDTOResponse> atualizarBackgroundSlot(
            @PathVariable("backgroundSlotId") Integer backgroundSlotId,
            @Valid @RequestBody BackgroundSlotDTORequest backgroundSlotDTORequest
    ) {
        return ResponseEntity.ok(backgroundslotService.atualizarBackgroundSlot(backgroundSlotId, backgroundSlotDTORequest));
    }
}
