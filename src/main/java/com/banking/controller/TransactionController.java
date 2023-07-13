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

@RestController
@RequiredArgsConstructor
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/create/{creditAccountId}")
    @ResponseStatus(HttpStatus.OK)
    public Transaction createTransaction(@RequestBody Transaction transaction, @PathVariable UUID creditAccountId) {
        return transactionService.createTransaction(transaction, creditAccountId);
    }

    @GetMapping("/find/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Transaction> findById(@PathVariable UUID id) {
        return transactionService.findById(id);
    }

    @GetMapping("/find/type")
    @ResponseStatus(HttpStatus.OK)
    public List<Transaction> findTransactionsByType(@RequestParam TransactionType type) {
        return transactionService.findTransactionsByType(type);
    }

    @GetMapping("/find/amount/{creditAccountId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Transaction> findTransactionsByAmount(@PathVariable UUID creditAccountId, @RequestParam BigDecimal amount) {
        return transactionService.findTransactionsByCreditAccountIdAndAmount(creditAccountId, amount);
    }

    @GetMapping("/find-all/type/{id}")
    public List<Transaction> findTransactionsByIdAndType(@PathVariable UUID id, @RequestParam TransactionType type) {
        return transactionService.findTransactionsByIdAndType(id, type);
    }

    @GetMapping("/find/description")
    @ResponseStatus(HttpStatus.OK)
    public List<Transaction> findTransactionsByDescription(@RequestParam String description) {
        return transactionService.findTransactionsByDescription(description);
    }
}
