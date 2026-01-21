package com.bankpay.app.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Transaction {
    private UUID id;
    private String cuentaOrigen;
    private String cuentaDestino;
    private BigDecimal monto;
    private String estado;
    private String bancoDestino;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
    private String mensajeRespuesta;
}

