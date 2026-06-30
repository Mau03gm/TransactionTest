package com.mauriciogomez.transaction2.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionRequest {

    private String operacion;

    private BigDecimal importe;

    private String cliente;

    private String secreto;
}
