package com.bankpay.app.infrastructure.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Entity
@Data
@Table(name = "Transacciones")
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
