package com.bankpay.app.application;

import com.bankpay.app.domain.Transaction;
import com.bankpay.app.infrastructure.adapters.in.web.dto.TransaccionRequestDTO;
import com.bankpay.app.infrastructure.mapper.TransactionMapper;
import com.bankpay.app.infrastructure.ports.out.TransactionRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

import java.time.LocalDateTime;

@ApplicationScoped
public class ProcessTransferUseCase {

    @Inject
    TransactionRepository transactionRepository;

    public Uni<Response> execute(TransaccionRequestDTO dto) {
        return Uni.createFrom().item(() -> {
                    // 1. Mapear DTO -> Dominio
                    Transaction transaction = TransactionMapper.toDomain(dto);

                    // 2. Validaciones de negocio
                    transaction.validarMonto();
                    transaction.validarCuentas();
                    transaction.setEstado("PENDIENTE");
                    transaction.setFechaCreacion(LocalDateTime.now());
                    transaction.setFechaActualizacion(LocalDateTime.now());

                    // 3. Persistir
                    transactionRepository.save(transaction);

                    // 4. Solo devolver status 200 sin body
                    return Response.status(Response.Status.OK).build();
                })
                .onFailure().recoverWithItem(err ->
                        Response.status(Response.Status.SERVICE_UNAVAILABLE) // 👈 503
                                .entity("Error procesando transacción: " + err.getMessage())
                                .build()
                );
    }

    // validar

    // guardar en base de datos

    //consumir api rest

    //poner mensaje en cola

    //actualizar data en bd

    // sacar mensaje de cola


}
