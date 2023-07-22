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

/**
 * This class provides the implementation for the ReportComponent interface,
 * which is responsible for generating various types of transaction reports.
 */
@RequiredArgsConstructor
@Slf4j
@Component
public class ReportComponentImpl implements ReportComponent {

    private final TransactionRepository transactionRepository;

    private final Receipt receipt;

    /**
     * Retrieve a list of transactions within a specified date range for a given credit account ID.
     *
     * @param creditId  The ID of the credit account.
     * @param startDate The start date of the report interval.
     * @param endDate   The end date of the report interval.
     * @return A list of transactions within the specified date range.
     */
    @Override
    public List<Transaction> giveIntervalReportByDate(UUID creditId, Timestamp startDate, Timestamp endDate) {
        return transactionRepository.findTransactionsByCreditAccountIdAndCreatedAtBetween(creditId, startDate, endDate);
    }

    /**
     * Retrieve a list of transactions for a given credit account ID and IBAN.
     *
     * @param creditId The ID of the credit account.
     * @param iBan     The IBAN (International Bank Account Number) associated with the transactions.
     * @return A list of transactions for the specified credit account and IBAN.
     */
    @Override
    public List<Transaction> giveReportByIBan(UUID creditId, String iBan) {
        return transactionRepository.findTransactionsByCreditAccountIdAndIBan(creditId, iBan);
    }

    /**
     * Retrieve a list of transactions for a given credit account ID and transaction type.
     *
     * @param creditId The ID of the credit account.
     * @param type     The type of transactions to retrieve.
     * @return A list of transactions for the specified credit account and transaction type.
     */
    @Override
    public List<Transaction> giveReportByType(UUID creditId, TransactionType type) {
        return transactionRepository.findTransactionsByCreditAccountIdAndType(creditId, type);
    }

    /**
     * Retrieve a list of transactions within a specified sum range for a given credit account ID.
     *
     * @param creditId The ID of the credit account.
     * @param minSum   The minimum sum amount for the transactions.
     * @param maxSum   The maximum sum amount for the transactions.
     * @return A list of transactions within the specified sum range.
     */

    @Override
    public List<Transaction> giveIntervalReportBySum(UUID creditId, BigDecimal minSum, BigDecimal maxSum) {
        return transactionRepository.findTransactionsByCreditAccountIdAndAmountBetween(creditId, minSum, maxSum);
    }

    /**
     * Generate a report based on a list of transactions and save it to a file.
     *
     * @param transactions The list of transactions to include in the report.
     * @return The file where the report is saved.
     */
    @Override
    public File saveReport(List<Transaction> transactions) {
        return receipt.saveReport(transactions);
    }
}
