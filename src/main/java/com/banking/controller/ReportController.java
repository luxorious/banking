package com.banking.controller;

import com.banking.entity.Transaction;
import com.banking.entity.entityenumerations.TransactionType;
import com.banking.service.component.interfaces.ReportComponent;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

/**
 * The ReportController class is a RESTful controller that handles HTTP requests related to generating and retrieving reports.
 * It provides endpoints to generate reports based on different criteria, such as date, transaction type, and sum.
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/report")
public class ReportController {
    private final ReportComponent reportComponent;

    /**
     * Generates a report of transactions within the specified date interval for a given credit account.
     *
     * @param creditAccountId The UUID of the credit account to generate the report for.
     * @param startDate       The start date of the interval.
     * @param endDate         The end date of the interval.
     * @return A list of transactions within the specified date interval.
     */
    @GetMapping("/date/{creditAccountId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Transaction> giveIntervalReportByDate(
            @PathVariable UUID creditAccountId,
            @RequestParam Timestamp startDate,
            @RequestParam Timestamp endDate) {
        return reportComponent.giveIntervalReportByDate(creditAccountId, startDate, endDate);
    }

    /**
     * Retrieves a report of transactions for a given credit account and IBAN number.
     *
     * @param creditAccountId The UUID of the credit account to generate the report for.
     * @param iBan            The IBAN number to filter the transactions.
     * @return A list of transactions for the specified credit account and IBAN.
     * @throws EntityNotFoundException if no transactions are found for the given credit account and IBAN.
     */
    @GetMapping("/i-ban/{creditAccountId}")
    public List<Transaction> giveReportByIBan(
            @PathVariable UUID creditAccountId,
            @RequestParam String iBan) {
        List<Transaction> transactions = reportComponent.giveReportByIBan(creditAccountId, iBan);
        if (!transactions.isEmpty()) {
            return transactions;
        } else {
            throw new EntityNotFoundException();
        }
    }
    /**
     * Retrieves a report of transactions for a given credit account and transaction type.
     *
     * @param creditAccountId The UUID of the credit account to generate the report for.
     * @param type            The transaction type to filter the transactions.
     * @return A ResponseEntity with a list of transactions for the specified credit account and transaction type,
     *         or a "no content" response if no transactions are found.
     */
    @GetMapping("/type/{creditAccountId}")
    public ResponseEntity<List<Transaction>> giveReportByType(
            @PathVariable UUID creditAccountId,
            @RequestParam TransactionType type) {
        List<Transaction> transactions = reportComponent.giveReportByType(creditAccountId, type);
        if (transactions != null && !transactions.isEmpty()) {
            return ResponseEntity.ok(transactions);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    /**
     * Generates a report of transactions within the specified sum range for a given credit account.
     *
     * @param creditAccountId The UUID of the credit account to generate the report for.
     * @param minSum          The minimum sum value for filtering transactions.
     * @param maxSum          The maximum sum value for filtering transactions.
     * @return A list of transactions within the specified sum range.
     */
    @GetMapping("/sum/{creditAccountId}")
    public List<Transaction> giveIntervalReportBySum(
            @PathVariable UUID creditAccountId,
            @RequestParam BigDecimal minSum,
            @RequestParam BigDecimal maxSum) {
        return reportComponent.giveIntervalReportBySum(creditAccountId, minSum, maxSum);
    }

    /**
     * Saves the provided list of transactions as a report file.
     *
     * @param transactions The list of transactions to be saved as a report.
     * @return A File object representing the saved report file.
     */
    @GetMapping("/save")
    public File saveReport(@RequestBody List<Transaction> transactions) {
        return reportComponent.saveReport(transactions);
    }
}
