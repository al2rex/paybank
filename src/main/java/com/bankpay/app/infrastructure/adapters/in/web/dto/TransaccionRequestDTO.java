package com.bankpay.app.infrastructure.adapters.in.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransaccionRequestDTO {
    private String cuentaOrigen;
    private String cuentaDestino;
    private BigDecimal monto;
    private String bancoDestino;
}
