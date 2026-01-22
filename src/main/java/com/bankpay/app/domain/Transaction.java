package com.bankpay.app.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
        this.estado = estado;
        this.bancoDestino = bancoDestino;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
        this.mensajeRespuesta = mensajeRespuesta;
    }



    // --- Validaciones de negocio ---
    public void validarMonto() {
        if (monto == null || monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor a cero");
        }
    }

    public void validarCuentas() {
        if (cuentaOrigen == null || cuentaOrigen.isBlank()) {
            throw new IllegalArgumentException("La cuenta origen no puede ser nula");
        }
        if (cuentaDestino == null || cuentaDestino.isBlank()) {
            throw new IllegalArgumentException("La cuenta destino no puede ser nula");
        }
    }

    // --- Cambio de estado ---
    public void marcarExitosa(String mensaje) {
        this.estado = "EXITOSA";
        this.mensajeRespuesta = mensaje;
        this.fechaActualizacion = LocalDateTime.now();
    }

    public void marcarFallida(String mensaje) {
        this.estado = "FALLIDA";
        this.mensajeRespuesta = mensaje;
        this.fechaActualizacion = LocalDateTime.now();
    }

}

