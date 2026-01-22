package com.bankpay.app.infrastructure.mapper;

import com.bankpay.app.domain.Transaction;
import com.bankpay.app.infrastructure.adapters.in.web.dto.TransaccionRequestDTO;
import com.bankpay.app.infrastructure.models.TransactionEntity;

import java.time.LocalDateTime;

public class TransactionMapper {

    // Dominio -> Entity
    public static TransactionEntity fromDomaintoEntity(Transaction trans) {
        return TransactionEntity.builder()
                .cuentaOrigen(trans.getCuentaOrigen())
                .bancoDestino(trans.getCuentaDestino())
                .monto(trans.getMonto())
                .bancoDestino(trans.getBancoDestino())
                .fechaCreacion(LocalDateTime.now())
                .build();
    }

    public static Transaction fromEntityToDomain(TransactionEntity entity){
        return Transaction.builder()
                .cuentaDestino(entity.getCuentaDestino())
                .cuentaOrigen(entity.getCuentaOrigen())
                .monto(entity.getMonto())
                .cuentaDestino(entity.getCuentaDestino())
                .build();
    }


    public static Transaction toDomain(TransaccionRequestDTO dto) {
        return Transaction.builder()
                .cuentaOrigen(dto.getCuentaOrigen())
                .cuentaDestino(dto.getCuentaDestino())
                .monto(dto.getMonto())
                .bancoDestino(dto.getBancoDestino())
                .build();
    }

    public static TransactionEntity toEntity(TransaccionRequestDTO trans) {
       return TransactionEntity.builder()
               .cuentaOrigen(trans.getCuentaOrigen())
               .bancoDestino(trans.getCuentaDestino())
               .monto(trans.getMonto())
               .bancoDestino(trans.getBancoDestino())
               .build();
    }

}
