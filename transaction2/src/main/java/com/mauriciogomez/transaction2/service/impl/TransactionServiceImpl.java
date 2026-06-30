package com.mauriciogomez.transaction2.service.impl;

import com.mauriciogomez.transaction2.dto.TransactionRequest;
import com.mauriciogomez.transaction2.dto.TransactionResponse;
import com.mauriciogomez.transaction2.dto.TransactionStatusUpdateRequest;
import com.mauriciogomez.transaction2.entity.TransactionEntity;
import com.mauriciogomez.transaction2.mapper.TransactionMapper;
import com.mauriciogomez.transaction2.repository.TransactionRepository;
import com.mauriciogomez.transaction2.service.TransactionService;
import com.mauriciogomez.transaction2.util.ReferenceGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl  implements TransactionService {

    private static final String STATUS_APPROVED  = "Aprobada";
    private static final String STATUS_CANCELLED = "Cancelada";

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

    @Override
    public void cancelTransaction(TransactionStatusUpdateRequest request) {

        if (!request.getEstatus().equalsIgnoreCase("cancelar")) {
            throw new IllegalArgumentException("El estatus debe ser cancelar");
        }
        int updatedRows = repository.updateStatus(request.getId(), request.getReferencia(), STATUS_CANCELLED);

        if (updatedRows == 0) {
            throw new RuntimeException("No existe transacción.");
        }
    }

    @Override
    public Page<TransactionResponse> getTransactions(int page, int size, String sortBy) {
        Pageable pageable =
                PageRequest.of(
                        page,
                        size,
                        Sort.by(sortBy).ascending()
                );
        return repository.findAll(pageable).map(mapper::toResponse);
    }
}
