package com.banking.controller;

import com.banking.entity.Transaction;
import com.banking.entity.entityenumerations.TransactionType;
import com.banking.service.interfaces.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * The TransactionController class is a REST ful controller that handles HTTP requests related to transactions.
 * It provides endpoints for creating, retrieving, and searching for transactions.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    /**
     * Creates a new transaction for the specified credit account.
     *
     * @param transaction     The transaction data to be created.
     * @param creditAccountId The UUID of the credit account for which the transaction is created.
     * @return The created transaction.
     */
    @PostMapping("/create/{creditAccountId}")
    @ResponseStatus(HttpStatus.OK)
    public Transaction createTransaction(@RequestBody Transaction transaction, @PathVariable UUID creditAccountId) {
        return transactionService.createTransaction(transaction, creditAccountId);
    }

    /**
     * Retrieves a transaction by its UUID.
     *
     * @param id The UUID of the transaction to retrieve.
     * @return An Optional containing the transaction if found, otherwise an empty Optional.
     */
    @GetMapping("/find/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Transaction> findById(@PathVariable UUID id) {
        return transactionService.findById(id);
    }

    /**
     * Retrieves transactions of a specific type.
     *
     * @param type The transaction type to filter the transactions.
     * @return A list of transactions of the specified type.
     */
    @GetMapping("/find/type")
    @ResponseStatus(HttpStatus.OK)
    public List<Transaction> findTransactionsByType(@RequestParam TransactionType type) {
        return transactionService.findTransactionsByType(type);
    }

    /**
     * Retrieves transactions by credit account ID and amount.
     *
     * @param creditAccountId The UUID of the credit account to filter the transactions.
     * @param amount          The amount to filter the transactions.
     * @return A list of transactions that match the credit account ID and amount.
     */
    @GetMapping("/find/amount/{creditAccountId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Transaction> findTransactionsByAmount(@PathVariable UUID creditAccountId, @RequestParam BigDecimal amount) {
        return transactionService.findTransactionsByCreditAccountIdAndAmount(creditAccountId, amount);
    }

    /**
     * Retrieves transactions by ID and type.
     *
     * @param id   The UUID of the transaction to filter.
     * @param type The transaction type to filter.
     * @return A list of transactions that match the specified ID and type.
     */
    @GetMapping("/find-all/type/{id}")
    public List<Transaction> findTransactionsByIdAndType(@PathVariable UUID id, @RequestParam TransactionType type) {
        return transactionService.findTransactionsByIdAndType(id, type);
    }

    /**
     * Retrieves transactions by description.
     *
     * @param description The description to filter the transactions.
     * @return A list of transactions that match the specified description.
     */
    @GetMapping("/find/description")
    @ResponseStatus(HttpStatus.OK)
    public List<Transaction> findTransactionsByDescription(@RequestParam String description) {
        return transactionService.findTransactionsByDescription(description);
    }
}
