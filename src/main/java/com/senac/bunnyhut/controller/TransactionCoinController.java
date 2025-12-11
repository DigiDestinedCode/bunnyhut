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
@RequestMapping("api/transactioncoin")
public class TransactionCoinController {

    private final TransactionCoinService transactioncoinService;

    public TransactionCoinController(TransactionCoinService transactioncoinService) {
        this.transactioncoinService = transactioncoinService;
    }

    @GetMapping("/list")
    @Operation(
            summary = "List all coin transactions",
            description = "Endpoint to list all coin transactions"
    )
    public ResponseEntity<List<TransactionCoinDTOResponse>> listTransactionCoins() {
        return ResponseEntity.ok(transactioncoinService.listTransactionCoins());
    }

    @GetMapping("/listById/{transactionCoinId}")
    @Operation(
            summary = "List coin transaction by ID",
            description = "Endpoint to list coin transaction by ID"
    )
    public ResponseEntity<TransactionCoinDTOResponse> getTransactionCoinById(@PathVariable("transactionCoinId") Integer transactionCoinId) {
        TransactionCoinDTOResponse transactioncoin = transactioncoinService.getTransactionCoinById(transactionCoinId);
        if (transactioncoin == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(transactioncoin);
        }
    }
    @PostMapping("/create")
    @Operation(
            summary = "Create new coin transaction",
            description = "Endpoint to create a new coin transaction record"
    )
    public ResponseEntity<TransactionCoinDTOResponse> createTransactionCoin(
            @Valid @RequestBody TransactionCoinDTORequest transactioncoin
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(transactioncoinService.createTransactionCoin(transactioncoin));
    }

    @PutMapping("/update/{transactionCoinId}")
    @Operation(
            summary = "Update all coin transaction data",
            description = "Endpoint to update the coin transaction record"
    )
    public ResponseEntity<TransactionCoinDTOResponse> updateTransactionCoin(
            @PathVariable("transactionCoinId") Integer transactionCoinId,
            @Valid @RequestBody TransactionCoinDTORequest transactioncoinDTORequest
    ) {
        return ResponseEntity.ok(transactioncoinService.updateTransactionCoin(transactionCoinId, transactioncoinDTORequest));
    }
}