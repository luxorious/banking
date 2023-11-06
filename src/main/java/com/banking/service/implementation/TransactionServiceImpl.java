package com.banking.service.implementation;

import com.banking.entity.Transaction;
import com.banking.entity.entityenumerations.TransactionType;
import com.banking.entity.pojo.CreditData;
import com.banking.repository.TransactionRepository;
import com.banking.service.interfaces.TransactionService;
import com.banking.service.interfaces.utility.ValidatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service implementation for managing transactions.
 */
@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final ValidatorService<Transaction> validatorService;

    /**
     * Creates a new transaction with the specified credit account ID.
     *
     * @param transaction     The Transaction object containing transaction-related information.
     * @param creditAccountId The UUID of the credit account associated with the transaction.
     * @return The created and saved Transaction object.
     */
    @Override
    public Transaction createTransaction(Transaction transaction, UUID creditAccountId) {
        transaction.setCreditAccountId(creditAccountId);
        return transactionRepository.save(transaction);
    }

    /**
     * Finds the transaction with the specified ID.
     *
     * @param uuid The UUID of the transaction to find.
     * @return The Transaction object with the specified ID, if found.
     */
    @Override
    public Optional<Transaction> findById(UUID uuid) {
        return transactionRepository.findById(uuid);
    }

    /**
     * Finds all transactions with the specified type.
     *
     * @param type The type of the transactions to find.
     * @return The list of transactions with the specified type.
     */
    @Override
    public List<Transaction> findTransactionsByType(TransactionType type) {
        return transactionRepository.findTransactionsByType(type);
    }

    /**
     * Finds all transactions with the specified ID and type.
     *
     * @param id   The UUID of the credit account associated with the transactions.
     * @param type The type of the transactions to find.
     * @return The list of transactions with the specified ID and type.
     */
    @Override
    public List<Transaction> findTransactionsByIdAndType(UUID id, TransactionType type) {
        return validatorService.checkList(transactionRepository.findTransactionsByCreditAccountIdAndType(id, type));
    }

    /**
     * Finds all transactions with the specified credit account ID and amount.
     *
     * @param creditAccountId The UUID of the credit account associated with the transactions.
     * @param amount          The amount of the transactions to find.
     * @return The list of transactions with the specified credit account ID and amount.
     */
    @Override
    public List<Transaction> findTransactionsByCreditAccountIdAndAmount(UUID creditAccountId, BigDecimal amount) {
        return transactionRepository.findTransactionsByCreditAccountIdAndAmount(creditAccountId, amount);
    }

    /**
     * Finds all transactions with the specified description.
     *
     * @param description The description of the transactions to find.
     * @return The list of transactions with the specified description.
     */
    @Override
    public List<Transaction> findTransactionsByDescription(String description) {
        return transactionRepository.findTransactionsByDescription(description);
    }

    /**
     * Finds all transactions with the specified credit account ID, type, and created timestamp within a specified range.
     *
     * @param creditData The CreditData object containing credit account ID and start date information.
     * @param type       The type of the transactions to find.
     * @return The list of transactions with the specified credit account ID, type, and created timestamp within the range.
     */
    @Override
    public List<Transaction> findTransactionsByCreditAccountIdAndTypeAndCreatedAtBetween(
            CreditData creditData, TransactionType type) {
        Timestamp endDate = new Timestamp(System.currentTimeMillis());
        Timestamp startDate = creditData.getStartDate();
        return validatorService.checkList(
                transactionRepository.findTransactionsByCreditAccountIdAndTypeAndCreatedAtBetween(
                        creditData.getId(), type, startDate, endDate
                ));
    }

    /**
     * Saves a transaction to the database.
     *
     * @param transaction The Transaction object to be saved.
     * @return The saved Transaction object.
     */
    @Override
    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
}
