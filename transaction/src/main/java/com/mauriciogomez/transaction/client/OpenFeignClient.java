package com.mauriciogomez.transaction.client;

import com.mauriciogomez.transaction.config.FeignConfig;
import com.mauriciogomez.transaction.dto.TransactionRequest;
import com.mauriciogomez.transaction.dto.TransactionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "transaction-api-2",
        url = "http://localhost:8081",
        configuration = FeignConfig.class
)
public interface OpenFeignClient {

    @PostMapping("/api/transactions")
    TransactionResponse createTransaction(
            @RequestBody TransactionRequest request
    );
}