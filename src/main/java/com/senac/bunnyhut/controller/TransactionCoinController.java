package com.senac.bunnyhut.controller;

import com.senac.bunnyhut.dto.request.TransactionCoinDTORequest;
import com.senac.bunnyhut.dto.response.TransactionCoinDTOResponse;
import com.senac.bunnyhut.service.TransactionCoinService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/transaction-coin")
public class TransactionCoinController {

    private final TransactionCoinService transactioncoinService;

    public TransactionCoinController(TransactionCoinService transactioncoinService) {
        this.transactioncoinService = transactioncoinService;
    }

    @GetMapping("/listar")
    @Operation(
            summary = "Listar transactioncoins",
            description = "Endpoint para listar todos os transactioncoins"
    )
    public ResponseEntity<List<TransactionCoinDTOResponse>> listTransactioncoins() {
        return ResponseEntity.ok(transactioncoinService.listTransactionCoins());
    }

    @GetMapping("/listarPorTransactioncoinId/{transactioncoinId}")
    @Operation(
            summary = "Listar transactioncoin pelo id de transactioncoin",
            description = "Endpoint para listar transactioncoin por Id de transactioncoin"
    )
    public ResponseEntity<TransactionCoinDTOResponse> listarPorTransactioncoinId(@PathVariable("transactioncoinId") Integer transactioncoinId) {
        TransactionCoinDTOResponse transactioncoin = transactioncoinService.listarPorTransactionCoinId(transactioncoinId);
        if (transactioncoin == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(transactioncoin);
        }
    }
    @PostMapping("/criar")
    @Operation(
            summary = "Criar novo transactioncoin",
            description = "Endpoint para criar um novo registro de transactioncoin"
    )
    public ResponseEntity<TransactionCoinDTOResponse> criarTransactioncoin(
            @Valid @RequestBody TransactionCoinDTORequest transactioncoin
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(transactioncoinService.criarTransactionCoin(transactioncoin));
    }

    @PutMapping("/atualizar/{transactioncoinId}")
    @Operation(
            summary = "Atualizar todos os dados do transactioncoin",
            description = "Endpoint para atualizar o registro de transactioncoin"
    )
    public ResponseEntity<TransactionCoinDTOResponse> atualizarTransactioncoin(
            @PathVariable("transactioncoinId") Integer transactioncoinId,
            @Valid @RequestBody TransactionCoinDTORequest transactioncoinDTORequest
    ) {
        return ResponseEntity.ok(transactioncoinService.atualizarTransactionCoin(transactioncoinId, transactioncoinDTORequest));
    }
}
