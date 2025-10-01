package com.senac.bunnyhut.controller;

import com.senac.bunnyhut.dto.request.Transaction_CoinDTORequest;
import com.senac.bunnyhut.dto.response.Transaction_CoinDTOResponse;
import com.senac.bunnyhut.dto.response.Transaction_CoinDTOUpdateResponse;
import com.senac.bunnyhut.service.Transaction_CoinService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/transaction-coin")
public class Transaction_CoinController {

    private final Transaction_CoinService transaction_coinService;

    public Transaction_CoinController(Transaction_CoinService transaction_coinService) {
        this.transaction_coinService = transaction_coinService;
    }

    @GetMapping("/listar")
    @Operation(
            summary = "Listar transaction_coins",
            description = "Endpoint para listar todos os transaction_coins"
    )
    public ResponseEntity<List<Transaction_CoinDTOResponse>> listTransaction_Coins() {
        return ResponseEntity.ok(transaction_coinService.listTransaction_Coins());
    }

    @GetMapping("/listarPorTransaction_CoinId/{transaction_coinId}")
    @Operation(
            summary = "Listar transaction_coin pelo id de transaction_coin",
            description = "Endpoint para listar transaction_coin por Id de transaction_coin"
    )
    public ResponseEntity<Transaction_CoinDTOResponse> listarPorTransaction_CoinId(@PathVariable("transaction_coinId") Integer transaction_coinId) {
        Transaction_CoinDTOResponse transaction_coin = transaction_coinService.listarPorTransaction_CoinId(transaction_coinId);
        if (transaction_coin == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(transaction_coin);
        }
    }
    @PostMapping("/criar")
    @Operation(
            summary = "Criar novo transaction_coin",
            description = "Endpoint para criar um novo registro de transaction_coin"
    )
    public ResponseEntity<Transaction_CoinDTOResponse> criarTransaction_Coin(
            @Valid @RequestBody Transaction_CoinDTORequest transaction_coin
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(transaction_coinService.criarTransaction_Coin(transaction_coin));
    }

    @PutMapping("/atualizar/{transaction_coinId}")
    @Operation(
            summary = "Atualizar todos os dados do transaction_coin",
            description = "Endpoint para atualizar o registro de transaction_coin"
    )
    public ResponseEntity<Transaction_CoinDTOResponse> atualizarTransaction_Coin(
            @PathVariable("transaction_coinId") Integer transaction_coinId,
            @Valid @RequestBody Transaction_CoinDTORequest transaction_coinDTORequest
    ) {
        return ResponseEntity.ok(transaction_coinService.atualizarTransaction_Coin(transaction_coinId, transaction_coinDTORequest));
    }

//    @PatchMapping("/atualizarStatus/{transaction_coinId}")
//    @Operation(
//            summary = "Atualizar campo status do transaction_coin",
//            description = "Endpoint para atualizar apenas o status do transaction_coin"
//    )
//    public ResponseEntity<Transaction_CoinDTOUpdateResponse> atualizarStatusTransaction_Coin(
//            @PathVariable("transaction_coinId") Integer transaction_coinId,
//            @Valid @RequestBody Transaction_CoinDTORequest transaction_coinDTOUpdateRequest
//    ) {
//        return ResponseEntity.ok(transaction_coinService.atualizarStatusTransaction_Coin(transaction_coinId, transaction_coinDTOUpdateRequest));
//    }

//    @DeleteMapping("/apagar/{transaction_coinId}")
//    @Operation(
//            summary = "Apagar registro do transaction_coin",
//            description = "Endpoint para apagar registro do transaction_coin"
//    )
//    public ResponseEntity<Void> apagarTransaction_Coin(@PathVariable("transaction_coinId") Integer transaction_coinId) {
//        transaction_coinService.apagarTransaction_Coin(transaction_coinId);
//        return ResponseEntity.noContent().build();
//    }
}
