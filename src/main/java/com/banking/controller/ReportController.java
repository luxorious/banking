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

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/report")
public class ReportController {
    private final ReportComponent reportComponent;

    @GetMapping("/date/{creditAccountId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Transaction> giveIntervalReportByDate(
            @PathVariable UUID creditAccountId,
            @RequestParam Timestamp startDate,
            @RequestParam Timestamp endDate) {
        return reportComponent.giveIntervalReportByDate(creditAccountId, startDate, endDate);
    }

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

    @GetMapping("/sum/{creditAccountId}")
    public List<Transaction> giveIntervalReportBySum(
            @PathVariable UUID creditAccountId,
            @RequestParam BigDecimal minSum,
            @RequestParam BigDecimal maxSum) {
        return reportComponent.giveIntervalReportBySum(creditAccountId, minSum, maxSum);
    }

    @GetMapping("/save")
    public File saveReport(@RequestBody List<Transaction> transactions) {
        return reportComponent.saveReport(transactions);
    }
}
