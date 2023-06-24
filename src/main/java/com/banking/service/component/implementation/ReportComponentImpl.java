package com.banking.service.component.implementation;

import com.banking.entity.Transaction;
import com.banking.entity.entityEnumerations.TransactionType;
import com.banking.repository.TransactionRepository;
import com.banking.service.component.interfaces.ReportComponent;
import com.banking.service.interfaces.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Component
public class ReportComponentImpl implements ReportComponent {
    private final TransactionRepository transactionRepository;
    @Override
    public List<Transaction> giveIntervalReportByDate(UUID creditId, Timestamp startDate, Timestamp endDate) {
        return transactionRepository.findTransactionsByCreditAccountIdAndCreatedAtBetween(creditId, startDate, endDate);
    }

    @Override
    public List<Transaction> giveReportByIBan(UUID creditId, String iBan) {
        return null;//transactionRepository.findTransactionsByCreditAccountIdAndIBan(creditId, iBan);
    }

    @Override
    public List<Transaction> giveReportByType(UUID creditId, TransactionType type) {
        return transactionRepository.findTransactionsByCreditAccountIdAndType(creditId, type);
    }

    @Override
    public List<Transaction> giveIntervalReportBySum(UUID creditId, BigDecimal minSum, BigDecimal maxSum) {
        return transactionRepository.findTransactionsByCreditAccountIdAndAmountBetween(creditId, minSum, maxSum);
    }
}
