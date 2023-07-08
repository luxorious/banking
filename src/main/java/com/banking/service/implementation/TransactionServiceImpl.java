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
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final ValidatorService<Transaction> validatorService;

    @Override
    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public Optional<Transaction> findById(UUID uuid) {
        return transactionRepository.findById(uuid);
    }

    @Override
    public List<Transaction> findTransactionsByType(TransactionType type) {
        return transactionRepository.findTransactionsByType(type);
    }

    @Override
    public List<Transaction> findTransactionsByIdAndType(UUID id, TransactionType type) {
        return validatorService.checkList(transactionRepository.findTransactionsByCreditAccountIdAndType(id, type));
    }

    @Override
    public List<Transaction> findTransactionsByAmount(BigDecimal amount) {
        return transactionRepository.findTransactionsByAmount(amount);
    }

    @Override
    public List<Transaction> findTransactionsByDescription(String description) {
        return transactionRepository.findTransactionsByDescription(description);
    }

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

    @Override
    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
}
