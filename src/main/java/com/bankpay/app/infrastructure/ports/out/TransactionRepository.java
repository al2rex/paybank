package com.bankpay.app.infrastructure.ports.out;

public interface TransactionRepository {
    public <T> T save( T reg );
}
