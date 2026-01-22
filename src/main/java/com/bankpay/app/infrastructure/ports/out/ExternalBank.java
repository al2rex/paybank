package com.bankpay.app.infrastructure.ports.out;

import com.bankpay.app.infrastructure.Dto.TransactionAPIResponseDTO;

public interface ExternalBank {
    TransactionAPIResponseDTO checkTransaction(Integer idTransaction, String nameBank);

}
