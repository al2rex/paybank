package com.bankpay.app.infrastructure.adapters.out;

import com.bankpay.app.infrastructure.ports.out.TransactionRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TransactionSqlServerRepository implements TransactionRepository {
    @Override
    public <T> T save(T reg) {
        return null;
    }
}
