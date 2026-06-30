package com.mauriciogomez.transaction.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionResponse {

    private Long id;

    private String estatus;

    private String referencia;

    private String operacion;

}
