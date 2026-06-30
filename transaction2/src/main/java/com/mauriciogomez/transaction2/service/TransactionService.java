package com.mauriciogomez.transaction2.service;

import com.mauriciogomez.transaction2.dto.TransactionRequest;
import com.mauriciogomez.transaction2.dto.TransactionResponse;

public interface TransactionService {
    
    TransactionResponse save(TransactionRequest request);
}
