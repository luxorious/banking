package com.banking.repository;

import com.banking.entity.Transaction;
import com.banking.entity.entityenumerations.TransactionType;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for managing Transaction entities.
 * This interface extends JpaRepository to provide basic CRUD operations for Transaction entities.
 */
@Repository
@NonNullApi
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    /**
     * Retrieves an optional Transaction entity based on the transaction ID.
     *
     * @param uuid The ID of the transaction to retrieve.
     * @return An optional Transaction entity with the specified ID.
     */
    Optional<Transaction> findById(UUID uuid);

    /**
     * Retrieves a list of Transaction entities based on the transaction type.
     *
     * @param type The type of the transactions to retrieve.
     * @return A list of Transaction entities with the specified type.
     */
    List<Transaction> findTransactionsByType(TransactionType type);

    /**
     * Retrieves a list of Transaction entities based on the credit account ID and amount.
     *
     * @param creditAccountId The ID of the credit account associated with the transactions.
     * @param amount          The amount of the transactions to retrieve.
     * @return A list of Transaction entities with the specified credit account ID and amount.
     */
    List<Transaction> findTransactionsByCreditAccountIdAndAmount(UUID creditAccountId, BigDecimal amount);

    /**
     * Retrieves a list of Transaction entities based on the transaction description.
     *
     * @param description The description of the transactions to retrieve.
     * @return A list of Transaction entities with the specified description.
     */
    List<Transaction> findTransactionsByDescription(String description);

    /**
     * Retrieves a list of Transaction entities based on the credit account ID and created timestamp
     * falling within the specified date range.
     *
     * @param creditId  The ID of the credit account associated with the transactions.
     * @param startDate The start date of the date range.
     * @param endDate   The end date of the date range.
     * @return A list of Transaction entities with the specified credit account ID and created date falling within the date range.
     */
    List<Transaction> findTransactionsByCreditAccountIdAndCreatedAtBetween(
            UUID creditId, Timestamp startDate, Timestamp endDate);

    /**
     * Retrieves a list of Transaction entities based on the credit account ID and transaction type.
     *
     * @param creditAccountId The ID of the credit account associated with the transactions.
     * @param type            The type of the transactions to retrieve.
     * @return A list of Transaction entities with the specified credit account ID and type.
     */
    List<Transaction> findTransactionsByCreditAccountIdAndType(UUID creditAccountId, TransactionType type);

    /**
     * Retrieves a list of Transaction entities based on the credit account ID and amount
     * falling within the specified amount range.
     *
     * @param creditAccountId The ID of the credit account associated with the transactions.
     * @param amount          The start amount of the amount range.
     * @param amount2         The end amount of the amount range.
     * @return A list of Transaction entities with the specified credit account ID and amount falling within the amount range.
     */
    List<Transaction> findTransactionsByCreditAccountIdAndAmountBetween(
            UUID creditAccountId, BigDecimal amount, BigDecimal amount2);

    /**
     * Retrieves a list of Transaction entities based on the credit account ID, transaction type,
     * and created timestamp falling within the specified date range.
     *
     * @param creditAccountId The ID of the credit account associated with the transactions.
     * @param type            The type of the transactions to retrieve.
     * @param createdAt       The start date of the date range.
     * @param createdAt2      The end date of the date range.
     * @return A list of Transaction entities with the specified credit account ID, type, and created date falling within the date range.
     */
    List<Transaction> findTransactionsByCreditAccountIdAndTypeAndCreatedAtBetween(
            UUID creditAccountId, TransactionType type, Timestamp createdAt, Timestamp createdAt2);

    /**
     * Retrieves a list of Transaction entities based on the credit account ID and receiver IBAN.
     *
     * @param creditAccountId The ID of the credit account associated with the transactions.
     * @param receiverIban    The IBAN of the receiver associated with the transactions.
     * @return A list of Transaction entities with the specified credit account ID and receiver IBAN.
     */
    @Query("SELECT tr FROM Transaction tr " +
            "WHERE tr.creditAccountId = :creditAccountId AND " +
            "tr.receiverIban = :receiverIban")
    List<Transaction> findTransactionsByCreditAccountIdAndIBan(UUID creditAccountId, String receiverIban);

}
