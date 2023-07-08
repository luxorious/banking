package com.banking.service.component.implementation;

import com.banking.entity.Transaction;
import com.banking.entity.entityenumerations.TransactionType;
import com.banking.repository.TransactionRepository;
import com.banking.service.component.interfaces.Receipt;
import com.banking.service.component.interfaces.ReportComponent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Component
public class ReportComponentImpl implements ReportComponent {

    private final TransactionRepository transactionRepository;

    private Receipt receipt;

    @Override
    public List<Transaction> giveIntervalReportByDate(UUID creditId, Timestamp startDate, Timestamp endDate) {
        return transactionRepository.findTransactionsByCreditAccountIdAndCreatedAtBetween(creditId, startDate, endDate);
    }

    @Override
    public List<Transaction> giveReportByIBan(UUID creditId, String iBan) {
        return transactionRepository.findTransactionsByCreditAccountIdAndIBan(creditId, iBan);
    }

    @Override
    public List<Transaction> giveReportByType(UUID creditId, TransactionType type) {
        return transactionRepository.findTransactionsByCreditAccountIdAndType(creditId, type);
    }

    @Override
    public List<Transaction> giveIntervalReportBySum(UUID creditId, BigDecimal minSum, BigDecimal maxSum) {
        return transactionRepository.findTransactionsByCreditAccountIdAndAmountBetween(creditId, minSum, maxSum);
    }

    @Override
    public File saveReport(List<Transaction> transactions) {
        return receipt.saveReport(transactions);
    }
}
