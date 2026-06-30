package com.mauriciogomez.transaction2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionStatusUpdateRequest {

    @NotNull
    private Long id;

    @NotBlank
    private String referencia;

    @NotBlank
    private String estatus;

}
