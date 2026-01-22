package com.bankpay.app.application;

import com.bankpay.app.domain.Transaction;
import com.bankpay.app.infrastructure.Dto.TransactionAPIResponseDTO;
import com.bankpay.app.infrastructure.adapters.in.web.dto.TransaccionRequestDTO;
import com.bankpay.app.infrastructure.ports.out.ExternalBank;
import com.bankpay.app.infrastructure.ports.out.TransactionRepository;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;


public class ProcessTransferUseCaseTest {
    private TransactionRepository transactionRepository;
    private ExternalBank externalBank;
    private ProcessTransferUseCase useCase;

    @BeforeEach
    void setUp() {
        transactionRepository = mock(TransactionRepository.class);
        externalBank = mock(ExternalBank.class);
        useCase = new ProcessTransferUseCase();
        useCase.transactionRepository = transactionRepository;
        useCase.externalBank = externalBank;
    }

    @Test
    void testExecuteSuccess() {
        // Arrange
        TransaccionRequestDTO dto = TransaccionRequestDTO.builder()
                .cuentaOrigen("00001210030")
                .cuentaDestino("00001210030")
                .monto(BigDecimal.TWO)
                .bancoDestino("DAVI").build();

        Transaction transaction = new Transaction();
        transaction.setId(1L);
        transaction.setBancoDestino("DAVI");

        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        TransactionAPIResponseDTO apiResponse = new TransactionAPIResponseDTO();
        apiResponse.setIdTransaction(Integer.parseInt("1"));
        apiResponse.setStatus("EXITOSA");

        when(externalBank.checkTransaction(anyInt(), anyString())).thenReturn(apiResponse);

        // Act
        Response response = useCase.execute(dto).await().indefinitely();

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        verify(transactionRepository).update(1L, "EXITOSA");
    }

    @Test
    void testExecuteExternalBankError() {
        // Arrange
        TransaccionRequestDTO dto = TransaccionRequestDTO.builder()
                .cuentaOrigen("00001210030")
                .cuentaDestino("00001210030")
                .monto(BigDecimal.TWO)
                .bancoDestino("DAVI").build();
        Transaction transaction = new Transaction();
        transaction.setId(1L);
        transaction.setBancoDestino("BANCO");

        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        // Simular excepción en externalBank
        when(externalBank.checkTransaction(anyInt(), anyString()))
                .thenThrow(new RuntimeException("API externa caída"));

        // Act
        Response response = useCase.execute(dto).await().indefinitely();

        // Assert
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
        assertTrue(response.getEntity().toString().contains("Error inesperado"));
        verify(transactionRepository).update(1L, "FALLIIDA");
    }

    @Test
    void testExecuteRepositoryFailure() {
        // Arrange
        TransaccionRequestDTO dto = new TransaccionRequestDTO();

        // Simular fallo en repositorio al guardar
        when(transactionRepository.save(any(Transaction.class)))
                .thenThrow(new RuntimeException("DB error"));

        // Act
        Response response = useCase.execute(dto).await().indefinitely();

        // Assert
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
        assertTrue(response.getEntity().toString().contains("Error procesando transacción"));
    }



}
