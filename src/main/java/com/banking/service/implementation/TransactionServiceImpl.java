package com.banking.service.implementation;

import com.banking.entity.Transaction;
import com.banking.entity.entityEnumerations.TransactionType;
import com.banking.repository.TransactionRepository;
import com.banking.service.interfaces.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Override
    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public Optional<Transaction> findById(UUID uuid) {
        return transactionRepository.findById(uuid);
    }

    @Override
    public List<Transaction> findTransactionByType(TransactionType type) {
        return transactionRepository.findTransactionByType(type);
    }

    @Override
    public List<Transaction> findTransactionByAmount(Double amount) {
        return transactionRepository.findTransactionByAmount(amount);
    }
}