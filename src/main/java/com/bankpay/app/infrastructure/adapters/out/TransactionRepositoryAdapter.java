package com.bankpay.app.infrastructure.adapters.out;

import com.bankpay.app.domain.Transaction;
import com.bankpay.app.infrastructure.mapper.TransactionMapper;
import com.bankpay.app.infrastructure.models.TransactionEntity;
import com.bankpay.app.infrastructure.ports.out.TransactionRepository;
import com.bankpay.app.infrastructure.repository.TransactionPanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class TransactionRepositoryAdapter implements TransactionRepository {

    @Inject
    TransactionPanacheRepository transactionPanacheRepository;

    @Override
    @Transactional
    public Transaction save(Transaction reg) {
        TransactionEntity entity = TransactionMapper.fromDomaintoEntity(reg);
        transactionPanacheRepository.persist(entity);
        TransactionEntity persisted = transactionPanacheRepository.findById(entity.getId());
        return TransactionMapper.fromEntityToDomain(persisted);
    }
}
