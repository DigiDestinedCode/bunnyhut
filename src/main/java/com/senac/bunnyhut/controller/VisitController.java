package com.senac.bunnyhut.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class VisitController {

    private final VisitService visitService;

    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @GetMapping("/listar")
    @Operation(
            summary = "Listar visits",
            description = "Endpoint para listar todos os visits"
    )
    public ResponseEntity<List<VisitDTOResponse>> listarVisits() {
        return ResponseEntity.ok(visitService.listarVisits());
    }

    @GetMapping("/listarPorVisitId/{visitId}")
    @Operation(
            summary = "Listar visit pelo id de visit",
            description = "Endpoint para listar visit por Id de visit"
    )
    public ResponseEntity<VisitDTOResponse> listarPorVisitId(@PathVariable("visitId") Integer visitId) {
        VisitDTOResponse visit = visitService.listarPorVisitId(visitId);
        if (visit == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(visit);
        }
    }
    @PostMapping("/criar")
    @Operation(
            summary = "Criar novo visit",
            description = "Endpoint para criar um novo registro de visit"
    )
    public ResponseEntity<VisitDTOResponse> criarVisit(
            @Valid @RequestBody VisitDTORequest visit
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(visitService.criarVisit(visit));
    }

    @PutMapping("/atualizar/{visitId}")
    @Operation(
            summary = "Atualizar todos os dados do visit",
            description = "Endpoint para atualizar o registro de visit"
    )
    public ResponseEntity<VisitDTOResponse> atualizarVisit(
            @PathVariable("visitId") Integer visitId,
            @Valid @RequestBody VisitDTORequest visitDTORequest
    ) {
        return ResponseEntity.ok(visitService.atualizarVisit(visitId, visitDTORequest));
    }

    @PatchMapping("/atualizarStatus/{visitId}")
    @Operation(
            summary = "Atualizar campo status do visit",
            description = "Endpoint para atualizar apenas o status do visit"
    )
    public ResponseEntity<VisitDTOUpdateResponse> atualizarStatusVisit(
            @PathVariable("visitId") Integer visitId,
            @Valid @RequestBody VisitDTORequest visitDTOUpdateRequest
    ) {
        return ResponseEntity.ok(visitService.atualizarStatusVisit(visitId, visitDTOUpdateRequest));
    }

    @DeleteMapping("/apagar/{visitId}")
    @Operation(
            summary = "Apagar registro do visit",
            description = "Endpoint para apagar registro do visit"
    )
    public ResponseEntity<Void> apagarVisit(@PathVariable("visitId") Integer visitId) {
        visitService.apagarVisit(visitId);
        return ResponseEntity.noContent().build();
    }
}
