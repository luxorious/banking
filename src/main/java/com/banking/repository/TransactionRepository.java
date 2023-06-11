package com.banking.repository;

import com.banking.entity.Transaction;
import com.banking.entity.entityEnumerations.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    @Override
    Optional<Transaction> findById(UUID uuid);

    List<Transaction> findTransactionByType(TransactionType type);

    List<Transaction> findTransactionByAmount(Double amount);

}