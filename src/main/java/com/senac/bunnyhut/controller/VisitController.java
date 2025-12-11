package com.senac.bunnyhut.controller;

import com.senac.bunnyhut.dto.request.VisitDTORequest;
import com.senac.bunnyhut.dto.response.VisitDTOResponse;
import com.senac.bunnyhut.service.VisitService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/visit")
public class VisitController {

    private final VisitService visitService;

    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @GetMapping("/list")
    @Operation(
            summary = "List all visits",
            description = "Endpoint to list all visits"
    )
    public ResponseEntity<List<VisitDTOResponse>> listVisits() {
        return ResponseEntity.ok(visitService.listVisits());
    }

    @GetMapping("/listById/{visitId}")
    @Operation(
            summary = "List visit by ID",
            description = "Endpoint to list visit by ID"
    )
    public ResponseEntity<VisitDTOResponse> getVisitById(@PathVariable("visitId") Integer visitId) {
        VisitDTOResponse visit = visitService.getVisitById(visitId);
        if (visit == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(visit);
        }
    }
    @PostMapping("/create")
    @Operation(
            summary = "Create new visit",
            description = "Endpoint to create a new visit record"
    )
    public ResponseEntity<VisitDTOResponse> createVisit(
            @Valid @RequestBody VisitDTORequest visit
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(visitService.createVisit(visit));
    }

    @PutMapping("/update/{visitId}")
    @Operation(
            summary = "Update all visit data",
            description = "Endpoint to update the visit record"
    )
    public ResponseEntity<VisitDTOResponse> updateVisit(
            @PathVariable("visitId") Integer visitId,
            @Valid @RequestBody VisitDTORequest visitDTORequest
    ) {
        return ResponseEntity.ok(visitService.updateVisit(visitId, visitDTORequest));
    }
}