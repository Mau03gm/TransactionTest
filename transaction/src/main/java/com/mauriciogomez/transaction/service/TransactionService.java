package com.mauriciogomez.transaction.service;

import com.mauriciogomez.transaction.dto.TransactionRequest;
import com.mauriciogomez.transaction.dto.TransactionResponse;

public interface TransactionService {
    TransactionResponse create(TransactionRequest request);
}