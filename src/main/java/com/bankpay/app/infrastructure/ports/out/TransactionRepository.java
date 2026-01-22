package com.bankpay.app.infrastructure.ports.out;

import com.bankpay.app.domain.Transaction;

public interface TransactionRepository {
    Transaction save(Transaction reg );

    Transaction findById(Long id);
    void update(Long id, String nuevoEstado);
}
