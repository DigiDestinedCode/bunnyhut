package com.senac.bunnyhut.controller;

public class BackgroundController {

    private final JogoService jogoService;

    public JogoController(JogoService jogoService) {
        this.jogoService = jogoService;
    }

    @GetMapping("/listar")
    @Operation(
            summary = "Listar jogos",
            description = "Endpoint para listar todos os jogos"
    )
    public ResponseEntity<List<JogoDTOResponse>> listarJogos() {
        return ResponseEntity.ok(jogoService.listarJogos());
    }

    @GetMapping("/listarPorJogoId/{jogoId}")
    @Operation(
            summary = "Listar jogo pelo id de jogo",
            description = "Endpoint para listar jogo por Id de jogo"
    )
    public ResponseEntity<JogoDTOResponse> listarPorJogoId(@PathVariable("jogoId") Integer jogoId) {
        JogoDTOResponse jogo = jogoService.listarPorJogoId(jogoId);
        if (jogo == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(jogo);
        }
    }
    @PostMapping("/criar")
    @Operation(
            summary = "Criar novo jogo",
            description = "Endpoint para criar um novo registro de jogo"
    )
    public ResponseEntity<JogoDTOResponse> criarJogo(
            @Valid @RequestBody JogoDTORequest jogo
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(jogoService.criarJogo(jogo));
    }

    @PutMapping("/atualizar/{jogoId}")
    @Operation(
            summary = "Atualizar todos os dados do jogo",
            description = "Endpoint para atualizar o registro de jogo"
    )
    public ResponseEntity<JogoDTOResponse> atualizarJogo(
            @PathVariable("jogoId") Integer jogoId,
            @Valid @RequestBody JogoDTORequest jogoDTORequest
    ) {
        return ResponseEntity.ok(jogoService.atualizarJogo(jogoId, jogoDTORequest));
    }

    @PatchMapping("/atualizarStatus/{jogoId}")
    @Operation(
            summary = "Atualizar campo status do jogo",
            description = "Endpoint para atualizar apenas o status do jogo"
    )
    public ResponseEntity<JogoDTOUpdateResponse> atualizarStatusJogo(
            @PathVariable("jogoId") Integer jogoId,
            @Valid @RequestBody JogoDTORequest jogoDTOUpdateRequest
    ) {
        return ResponseEntity.ok(jogoService.atualizarStatusJogo(jogoId, jogoDTOUpdateRequest));
    }

    @DeleteMapping("/apagar/{jogoId}")
    @Operation(
            summary = "Apagar registro do jogo",
            description = "Endpoint para apagar registro do jogo"
    )
    public ResponseEntity<Void> apagarJogo(@PathVariable("jogoId") Integer jogoId) {
        jogoService.apagarJogo(jogoId);
        return ResponseEntity.noContent().build();
    }
}
