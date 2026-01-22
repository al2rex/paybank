package com.bankpay.app.infrastructure.ports.out;

import com.bankpay.app.domain.Transaction;

public interface TransactionRepository {
    Transaction save(Transaction reg );
}
