package com.bankpay.app.infrastructure.repository;

import com.bankpay.app.infrastructure.models.TransactionEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TransactionPanacheRepository implements PanacheRepository<TransactionEntity> {
}
