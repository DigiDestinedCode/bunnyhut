package com.senac.bunnyhut.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class RabbitController {

    private final RabbitService rabbitService;

    public RabbitController(RabbitService rabbitService) {
        this.rabbitService = rabbitService;
    }

    @GetMapping("/listar")
    @Operation(
            summary = "Listar rabbits",
            description = "Endpoint para listar todos os rabbits"
    )
    public ResponseEntity<List<RabbitDTOResponse>> listarRabbits() {
        return ResponseEntity.ok(rabbitService.listarRabbits());
    }

    @GetMapping("/listarPorRabbitId/{rabbitId}")
    @Operation(
            summary = "Listar rabbit pelo id de rabbit",
            description = "Endpoint para listar rabbit por Id de rabbit"
    )
    public ResponseEntity<RabbitDTOResponse> listarPorRabbitId(@PathVariable("rabbitId") Integer rabbitId) {
        RabbitDTOResponse rabbit = rabbitService.listarPorRabbitId(rabbitId);
        if (rabbit == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(rabbit);
        }
    }
    @PostMapping("/criar")
    @Operation(
            summary = "Criar novo rabbit",
            description = "Endpoint para criar um novo registro de rabbit"
    )
    public ResponseEntity<RabbitDTOResponse> criarRabbit(
            @Valid @RequestBody RabbitDTORequest rabbit
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(rabbitService.criarRabbit(rabbit));
    }

    @PutMapping("/atualizar/{rabbitId}")
    @Operation(
            summary = "Atualizar todos os dados do rabbit",
            description = "Endpoint para atualizar o registro de rabbit"
    )
    public ResponseEntity<RabbitDTOResponse> atualizarRabbit(
            @PathVariable("rabbitId") Integer rabbitId,
            @Valid @RequestBody RabbitDTORequest rabbitDTORequest
    ) {
        return ResponseEntity.ok(rabbitService.atualizarRabbit(rabbitId, rabbitDTORequest));
    }

    @PatchMapping("/atualizarStatus/{rabbitId}")
    @Operation(
            summary = "Atualizar campo status do rabbit",
            description = "Endpoint para atualizar apenas o status do rabbit"
    )
    public ResponseEntity<RabbitDTOUpdateResponse> atualizarStatusRabbit(
            @PathVariable("rabbitId") Integer rabbitId,
            @Valid @RequestBody RabbitDTORequest rabbitDTOUpdateRequest
    ) {
        return ResponseEntity.ok(rabbitService.atualizarStatusRabbit(rabbitId, rabbitDTOUpdateRequest));
    }

    @DeleteMapping("/apagar/{rabbitId}")
    @Operation(
            summary = "Apagar registro do rabbit",
            description = "Endpoint para apagar registro do rabbit"
    )
    public ResponseEntity<Void> apagarRabbit(@PathVariable("rabbitId") Integer rabbitId) {
        rabbitService.apagarRabbit(rabbitId);
        return ResponseEntity.noContent().build();
    }
}
