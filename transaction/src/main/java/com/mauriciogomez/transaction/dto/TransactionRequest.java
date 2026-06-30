package com.mauriciogomez.transaction.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionRequest {

    @Pattern(regexp = "^[a-zA-Z]+$", message = "Operacion solo debe contener letras")
    @NotBlank(message = "operacion es obligatoria")
    private String operacion;

    @NotNull(message = "importe es obligatorio")
    private BigDecimal importe;

    @Pattern(regexp = "^[a-zA-Z]+$", message = "Cliente solo debe contener letras")
    @NotBlank(message = "cliente es obligatorio")
    private String cliente;

    @NotBlank(message = "secreto es obligatorio")
    private String secreto;
}
