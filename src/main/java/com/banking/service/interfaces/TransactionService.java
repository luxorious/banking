package com.banking.service.interfaces;

import com.banking.entity.Transaction;
import com.banking.entity.entityenumerations.TransactionType;
import com.banking.entity.pojo.CreditData;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * The TransactionService interface defines the contract for managing transactions in the banking system.
 * It provides methods for creating, retrieving, and searching for transactions based on various criteria.
 */
public interface TransactionService {
    /**
     * Creates a new transaction associated with the specified debit account and saves it to the database.
     *
     * @param transaction    The transaction to be created.
     * @param debitAccountId The ID of the debit account associated with the transaction.
     * @return The created transaction.
     */
    Transaction createTransaction(Transaction transaction, UUID debitAccountId);

    /**
     * Retrieves the transaction with the specified ID.
     *
     * @param uuid The ID of the transaction to be retrieved.
     * @return An optional containing the transaction with the specified ID, or an empty optional if not found.
     */
    Optional<Transaction> findById(UUID uuid);

    /**
     * Retrieves a list of transactions with the specified transaction type.
     *
     * @param type The type of transactions to be retrieved (e.g., credit, debit).
     * @return The list of transactions with the specified type.
     */
    List<Transaction> findTransactionsByType(TransactionType type);

    /**
     * Retrieves a list of transactions with the specified account ID and transaction type.
     *
     * @param id   The ID of the account to filter transactions.
     * @param type The type of transactions to be retrieved (e.g., credit, debit).
     * @return The list of transactions with the specified account ID and type.
     */
    List<Transaction> findTransactionsByIdAndType(UUID id, TransactionType type);

    /**
     * Retrieves a list of transactions with the specified credit account ID and amount.
     *
     * @param creditAccountId The ID of the credit account to filter transactions.
     * @param amount          The amount of the transactions to be retrieved.
     * @return The list of transactions with the specified credit account ID and amount.
     */
    List<Transaction> findTransactionsByCreditAccountIdAndAmount(UUID creditAccountId, BigDecimal amount);

    /**
     * Retrieves a list of transactions with the specified description.
     *
     * @param description The description of the transactions to be retrieved.
     * @return The list of transactions with the specified description.
     */
    List<Transaction> findTransactionsByDescription(String description);

    /**
     * Retrieves a list of transactions with the specified credit account ID, transaction type,
     * and created timestamp falling within a specified date range.
     *
     * @param creditData The credit data containing the credit account ID and the start date.
     * @param type       The type of transactions to be retrieved (e.g., credit, debit).
     * @return The list of transactions with the specified credit account ID, type, and date range.
     */
    List<Transaction> findTransactionsByCreditAccountIdAndTypeAndCreatedAtBetween(
            CreditData creditData, TransactionType type);

    /**
     * Saves the given transaction to the database.
     *
     * @param transaction The transaction to be saved.
     * @return The saved transaction.
     */
    Transaction save(Transaction transaction);
}
