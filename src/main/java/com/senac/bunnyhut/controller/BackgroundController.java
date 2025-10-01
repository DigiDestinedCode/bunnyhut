package com.senac.bunnyhut.controller;

import com.senac.bunnyhut.dto.request.BackgroundDTORequest;
import com.senac.bunnyhut.dto.response.BackgroundDTOResponse;
import com.senac.bunnyhut.dto.response.BackgroundDTOUpdateResponse;
import com.senac.bunnyhut.service.BackgroundService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categoria")
public class BackgroundController {

    private final BackgroundService backgroundService;

    public BackgroundController(BackgroundService backgroundService) {
        this.backgroundService = backgroundService;
    }

    @GetMapping("/listar")
    @Operation(
            summary = "Listar backgrounds",
            description = "Endpoint para listar todos os backgrounds"
    )
    public ResponseEntity<List<BackgroundDTOResponse>> listarBackgrounds() {
        return ResponseEntity.ok(backgroundService.listBackgrounds());
    }

    @GetMapping("/listarPorBackgroundId/{backgroundId}")
    @Operation(
            summary = "Listar background pelo id de background",
            description = "Endpoint para listar background por Id de background"
    )
    public ResponseEntity<BackgroundDTOResponse> listarPorBackgroundId(@PathVariable("backgroundId") Integer backgroundId) {
        BackgroundDTOResponse background = backgroundService.listarPorBackgroundId(backgroundId);
        if (background == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(background);
        }
    }
    @PostMapping("/criar")
    @Operation(
            summary = "Criar novo background",
            description = "Endpoint para criar um novo registro de background"
    )
    public ResponseEntity<BackgroundDTOResponse> criarBackground(
            @Valid @RequestBody BackgroundDTORequest background
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(backgroundService.criarBackground(background));
    }

    @PutMapping("/atualizar/{backgroundId}")
    @Operation(
            summary = "Atualizar todos os dados do background",
            description = "Endpoint para atualizar o registro de background"
    )
    public ResponseEntity<BackgroundDTOResponse> atualizarBackground(
            @PathVariable("backgroundId") Integer backgroundId,
            @Valid @RequestBody BackgroundDTORequest backgroundDTORequest
    ) {
        return ResponseEntity.ok(backgroundService.atualizarBackground(backgroundId, backgroundDTORequest));
    }

//    @PatchMapping("/atualizarStatus/{backgroundId}")
//    @Operation(
//            summary = "Atualizar campo status do background",
//            description = "Endpoint para atualizar apenas o status do background"
//    )
//    public ResponseEntity<BackgroundDTOUpdateResponse> atualizarStatusBackground(
//            @PathVariable("backgroundId") Integer backgroundId,
//            @Valid @RequestBody BackgroundDTORequest backgroundDTOUpdateRequest
//    ) {
//        return ResponseEntity.ok(backgroundService.atualizarStatusBackground(backgroundId, backgroundDTOUpdateRequest));
//    }

//    @DeleteMapping("/apagar/{backgroundId}")
//    @Operation(
//            summary = "Apagar registro do background",
//            description = "Endpoint para apagar registro do background"
//    )
//    public ResponseEntity<Void> apagarBackground(@PathVariable("backgroundId") Integer backgroundId) {
//        backgroundService.apagarBackground(backgroundId);
//        return ResponseEntity.noContent().build();
//    }
}
