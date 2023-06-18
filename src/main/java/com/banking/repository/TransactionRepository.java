package com.banking.repository;

import com.banking.entity.Transaction;
import com.banking.entity.entityEnumerations.TransactionType;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@NonNullApi
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    @Override
    Optional<Transaction> findById(UUID uuid);

    List<Transaction> findTransactionsByType(TransactionType type);

    List<Transaction> findTransactionsByAmount(Double amount);

    List<Transaction> findTransactionsByDescription(String description);

}
