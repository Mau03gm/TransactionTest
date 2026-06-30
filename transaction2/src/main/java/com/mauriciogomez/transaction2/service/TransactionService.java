package com.mauriciogomez.transaction2.service;

import com.mauriciogomez.transaction2.dto.TransactionRequest;
import com.mauriciogomez.transaction2.dto.TransactionResponse;
import com.mauriciogomez.transaction2.dto.TransactionStatusUpdateRequest;
import org.springframework.data.domain.Page;

public interface TransactionService {
    
    TransactionResponse save(TransactionRequest request);
    void cancelTransaction(TransactionStatusUpdateRequest request);
    Page<TransactionResponse> getTransactions(
            int page,
            int size,
            String sortBy
    );
}
