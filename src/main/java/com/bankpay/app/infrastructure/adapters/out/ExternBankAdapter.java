package com.bankpay.app.infrastructure.adapters.out;

import com.bankpay.app.infrastructure.Dto.TransactionAPIResponseDTO;
import com.bankpay.app.infrastructure.ports.out.ExternalBank;
import com.bankpay.app.infrastructure.rest.TransactionNodeBank;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class ExternBankAdapter implements ExternalBank {

    @Inject
    @RestClient
    TransactionNodeBank transactionNodeBank;

    @Override
    public TransactionAPIResponseDTO checkTransaction(Integer idTransaction, String nameBank) {
        String response = transactionNodeBank.checkTrasaction(idTransaction, nameBank);

        // Ejemplo de respuesta: "100|FAIL"
        if (response == null || !response.contains("|")) {
            return new TransactionAPIResponseDTO(idTransaction, "ERROR");
        }

        String[] parts = response.split("\\|");
        try {
            Integer id = Integer.parseInt(parts[0]);
            String status = parts[1];
            return new TransactionAPIResponseDTO(id, status);
        } catch (Exception e) {
            return new TransactionAPIResponseDTO(idTransaction, "ERROR");
        }

    }
}
