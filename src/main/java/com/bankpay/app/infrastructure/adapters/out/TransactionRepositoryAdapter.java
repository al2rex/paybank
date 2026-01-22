package com.bankpay.app.infrastructure.adapters.out;

import com.bankpay.app.domain.Transaction;
import com.bankpay.app.infrastructure.mapper.TransactionMapper;
import com.bankpay.app.infrastructure.models.TransactionEntity;
import com.bankpay.app.infrastructure.ports.out.TransactionRepository;
import com.bankpay.app.infrastructure.repository.TransactionPanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;

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

    @Override
    @Transactional
    public Transaction findById(Long id) {
        TransactionEntity entity = transactionPanacheRepository.findById(id);
        return TransactionMapper.fromEntityToDomain(entity);


    }

    @Override
    @Transactional
    public void update(Long id, String nuevoEstado) {

        transactionPanacheRepository.update("estado = ?1, fechaActualizacion = ?2 where id = ?3",
                nuevoEstado, LocalDateTime.now(), id);
    }

}
