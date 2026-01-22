package com.bankpay.app.domain;

import com.bankpay.app.infrastructure.constants.Constants;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.bankpay.app.infrastructure.constants.Constants.*;

@Data
@Builder
@NoArgsConstructor
public class Transaction {
    private Long id;
    private String cuentaOrigen;
    private String cuentaDestino;
    private BigDecimal monto;
    private String estado;
    private String bancoDestino;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
    private String mensajeRespuesta;

    public Transaction(Long id,
                       String cuentaOrigen,
                       String cuentaDestino,
                       BigDecimal monto,
                       String estado,
                       String bancoDestino,
                       LocalDateTime fechaCreacion,
                       LocalDateTime fechaActualizacion,
                       String mensajeRespuesta) {
        this.id = id;
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
        this.monto = monto;
        this.estado = PENDIENTE_STATUS;
        this.bancoDestino = bancoDestino;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
        this.mensajeRespuesta = mensajeRespuesta;
    }



    // --- Validaciones de negocio ---
    public void validarMonto() {
        if (monto == null || monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(ERROR_MONTO);
        }
    }

    public void validarCuentas() {
        if (cuentaOrigen == null || cuentaOrigen.isBlank()) {
            throw new IllegalArgumentException(CUENTA_ORIGEN_MSG);
        }
        if (cuentaDestino == null || cuentaDestino.isBlank()) {
            throw new IllegalArgumentException(CUENTA_DESTINO_MSG);
        }

        if (bancoDestino == null || bancoDestino.isBlank()) {
            throw new IllegalArgumentException(BANCO_DESTINO_MSG);
        }
    }
}

