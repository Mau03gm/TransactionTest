package com.mauriciogomez.transaction2.service.impl;

import com.mauriciogomez.transaction2.dto.TransactionRequest;
import com.mauriciogomez.transaction2.dto.TransactionResponse;
import com.mauriciogomez.transaction2.entity.TransactionEntity;
import com.mauriciogomez.transaction2.mapper.TransactionMapper;
import com.mauriciogomez.transaction2.repository.TransactionRepository;
import com.mauriciogomez.transaction2.service.TransactionService;
import com.mauriciogomez.transaction2.util.ReferenceGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl  implements TransactionService {

    private static final String STATUS_APPROVED  = "Aprobada";

    @Autowired
    private ReferenceGenerator referenceGenerator;
    @Autowired
    private TransactionMapper mapper;
    @Autowired
    private TransactionRepository repository;

    @Override
    public TransactionResponse save(TransactionRequest request) {
        TransactionEntity entity = mapper.toEntity(request);

        entity.setReferencia(referenceGenerator.generateReference());

        entity.setEstatus(STATUS_APPROVED);

        TransactionEntity saved = repository.save(entity);

        return mapper.toResponse(saved);
    }
}
