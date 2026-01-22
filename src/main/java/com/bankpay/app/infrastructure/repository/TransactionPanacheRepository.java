package com.bankpay.app.infrastructure.repository;

import com.bankpay.app.infrastructure.models.TransactionEntity;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TransactionPanacheRepository implements ReactivePanacheRepository<TransactionEntity> {
}
