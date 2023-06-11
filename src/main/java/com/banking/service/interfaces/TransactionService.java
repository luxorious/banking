package com.banking.service.interfaces;

import com.banking.entity.Transaction;
import com.banking.entity.entityEnumerations.TransactionType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionService {
    Transaction createTransaction(Transaction transaction);

    Optional<Transaction> findById(UUID uuid);

    List<Transaction> findTransactionByType(TransactionType type);

    List<Transaction> findTransactionByAmount(Double amount);
}
