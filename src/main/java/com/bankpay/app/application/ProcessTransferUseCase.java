package com.bankpay.app.application;

import com.bankpay.app.domain.Transaction;
import com.bankpay.app.infrastructure.Dto.TransactionAPIResponseDTO;
import com.bankpay.app.infrastructure.adapters.in.web.dto.TransaccionRequestDTO;
import com.bankpay.app.infrastructure.mapper.TransactionMapper;
import com.bankpay.app.infrastructure.ports.out.ExternalBank;
import com.bankpay.app.infrastructure.ports.out.TransactionRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@ApplicationScoped
@AllArgsConstructor
@NoArgsConstructor
public class ProcessTransferUseCase {

    @Inject
    TransactionRepository transactionRepository;

    @Inject
    ExternalBank externalBank;

    public Uni<Response> execute(TransaccionRequestDTO dto) {
        return Uni.createFrom().item(() -> {
            Transaction transaction = TransactionMapper.toDomain(dto);

            transaction.validarMonto();
            transaction.validarCuentas();
            transaction.setEstado("PENDIENTE");
            transaction.setFechaCreacion(LocalDateTime.now());
            transaction.setFechaActualizacion(LocalDateTime.now());

            Transaction transactionPersiste = transactionRepository.save(transaction);

            try {

                TransactionAPIResponseDTO responseExternalTransaction = externalBank
                            .checkTransaction(transactionPersiste.getId().intValue(),transactionPersiste.getBancoDestino());

                transactionRepository.update(Long.valueOf(responseExternalTransaction.getIdTransaction()), responseExternalTransaction.getStatus());

                return Response.status(Response.Status.OK).build();
            } catch (Exception e) {

                transactionRepository.update(transactionPersiste.getId(), "FALLIIDA");

                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity("Error inesperado: " + e.getMessage())
                        .build();
            }

        })
        .onFailure().recoverWithItem(err ->
                Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity("Error procesando transacción: " + err.getMessage())
                        .build()
        );
    }
}
