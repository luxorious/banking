package com.banking.service.component.interfaces;

import com.banking.entity.Transaction;
import com.banking.entity.entityenumerations.TransactionType;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

/**
 * The ReportComponent interface provides methods for generating and saving transaction reports based on various criteria.
 */
public interface ReportComponent {
    /**
     * Generates a transaction report for a specific credit account within a specified date interval.
     *
     * @param creditAccountId The ID of the credit account.
     * @param startDate       The start date of the interval.
     * @param endDate         The end date of the interval.
     * @return A list of transactions within the specified date interval.
     */
    List<Transaction> giveIntervalReportByDate(UUID creditAccountId, Timestamp startDate, Timestamp endDate);

    /**
     * Generates a transaction report for a specific credit account and IBAN.
     *
     * @param creditAccountId The ID of the credit account.
     * @param iBan            The IBAN to filter transactions.
     * @return A list of transactions for the specified credit account and IBAN.
     */
    List<Transaction> giveReportByIBan(UUID creditAccountId, String iBan);

    /**
     * Generates a transaction report for a specific credit account and transaction type.
     *
     * @param creditAccountId The ID of the credit account.
     * @param type            The transaction type to filter transactions.
     * @return A list of transactions for the specified credit account and transaction type.
     */
    List<Transaction> giveReportByType(UUID creditAccountId, TransactionType type);

    /**
     * Generates a transaction report for a specific credit account within a specified sum interval.
     *
     * @param creditAccountId The ID of the credit account.
     * @param minSum          The minimum amount of the transaction.
     * @param maxSum          The maximum amount of the transaction.
     * @return A list of transactions within the specified sum interval.
     */
    List<Transaction> giveIntervalReportBySum(UUID creditAccountId, BigDecimal minSum, BigDecimal maxSum);

    /**
     * Saves the transaction report as a text file.
     *
     * @param transactions The list of transactions to include in the report.
     * @return The generated file containing the transaction report.
     */
    File saveReport(List<Transaction> transactions);
}
