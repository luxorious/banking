package com.banking.service.interfaces;

import com.banking.entity.Transaction;
import com.banking.entity.entityenumerations.TransactionType;
import com.banking.entity.pojo.CreditData;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionService {

    Transaction createTransaction(Transaction transaction, UUID debitAccountId);

    Optional<Transaction> findById(UUID uuid);

    List<Transaction> findTransactionsByType(TransactionType type);


    List<Transaction> findTransactionsByIdAndType(UUID id, TransactionType type);

    List<Transaction> findTransactionsByCreditAccountIdAndAmount(UUID creditAccountId, BigDecimal amount);

    List<Transaction> findTransactionsByDescription(String description);

    List<Transaction> findTransactionsByCreditAccountIdAndTypeAndCreatedAtBetween(
            CreditData creditData, TransactionType type);

    Transaction save(Transaction transaction);
}
