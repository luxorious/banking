package com.banking.service.interfaces;

import com.banking.entity.Transaction;
import com.banking.entity.entityEnumerations.TransactionType;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionService {
    Transaction createTransaction(Transaction transaction);

    Optional<Transaction> findById(UUID uuid);

    List<Transaction> findTransactionsByType(TransactionType type);

    List<Transaction> findTransactionsByAmount(BigDecimal amount);

    List<Transaction> findTransactionsByDescription(String description);
}
