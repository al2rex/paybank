package com.bankpay.app.infrastructure.models;

import jakarta.persistence.*;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Entity
@Table(name = "Transacciones")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 30)
    private String cuentaOrigen;

    @Column(nullable = false, length = 30)
    private String cuentaDestino;

    @Column(nullable = false)
    private BigDecimal monto;

    @Column(nullable = false, length = 20)

    private String estado;

    @Column(nullable = false)
    private String bancoDestino;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
    private String mensajeRespuesta;
}
