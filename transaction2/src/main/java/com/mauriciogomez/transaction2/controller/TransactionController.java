package com.mauriciogomez.transaction2.controller;

import com.mauriciogomez.transaction2.dto.TransactionRequest;
import com.mauriciogomez.transaction2.dto.TransactionResponse;
import com.mauriciogomez.transaction2.dto.TransactionStatusUpdateRequest;
import com.mauriciogomez.transaction2.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/transactions")
@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionResponse> saveTransaction(@Valid @RequestBody TransactionRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(transactionService.save(request));
    }

    @PatchMapping("/status")
    public ResponseEntity<Void> cancelTransaction(
            @Valid @RequestBody TransactionStatusUpdateRequest request) {

        transactionService.cancelTransaction(request);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<TransactionResponse>>getTransactions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {

        return ResponseEntity.ok(transactionService.getTransactions(
                page,
                size,
                sortBy
        ));

    }

}
