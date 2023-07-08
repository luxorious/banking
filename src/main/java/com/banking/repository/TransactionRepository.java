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

@Repository
@NonNullApi
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    Optional<Transaction> findById(UUID uuid);

    List<Transaction> findTransactionsByType(TransactionType type);

    List<Transaction> findTransactionsByAmount(BigDecimal amount);

    List<Transaction> findTransactionsByDescription(String description);

    List<Transaction> findTransactionsByCreditAccountIdAndCreatedAtBetween(
            UUID creditId, Timestamp startDate, Timestamp endDate);

    List<Transaction> findTransactionsByCreditAccountIdAndType(UUID creditAccountId, TransactionType type);

    List<Transaction> findTransactionsByCreditAccountIdAndAmountBetween(
            UUID creditAccountId, BigDecimal amount, BigDecimal amount2);

    List<Transaction> findTransactionsByIdAndType(UUID id, TransactionType type);

    List<Transaction> findTransactionsByCreditAccountIdAndTypeAndCreatedAtBetween(
            UUID creditAccountId, TransactionType type, Timestamp createdAt, Timestamp createdAt2);

    @Query("SELECT tr FROM Transaction tr " +
            "WHERE tr.creditAccountId = :creditAccountId AND " +
            "tr.iBan = :iBan")
    List<Transaction> findTransactionsByCreditAccountIdAndIBan(UUID creditAccountId, String iBan);

}
