package com.mauriciogomez.transaction2.mapper;

import com.mauriciogomez.transaction2.dto.TransactionRequest;
import com.mauriciogomez.transaction2.dto.TransactionResponse;
import com.mauriciogomez.transaction2.entity.TransactionEntity;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

    public TransactionEntity toEntity(TransactionRequest request) {

        return TransactionEntity.builder()
                .operacion(request.getOperacion())
                .importe(request.getImporte())
                .cliente(request.getCliente())
                .secreto(request.getSecreto())
                .build();

    }

    public TransactionResponse toResponse(TransactionEntity entity) {

        return TransactionResponse.builder()
                .id(entity.getId())
                .estatus(entity.getEstatus())
                .referencia(entity.getReferencia())
                .operacion(entity.getOperacion())
                .build();

    }
}
