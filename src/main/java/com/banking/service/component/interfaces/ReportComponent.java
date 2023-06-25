package com.banking.service.component.interfaces;

import com.banking.entity.Transaction;
import com.banking.entity.entityenumerations.TransactionType;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public interface ReportComponent {
    List<Transaction> giveIntervalReportByDate(UUID creditAccountId, Timestamp startDate, Timestamp endDate);
    List<Transaction> giveReportByIBan(UUID creditAccountId, String iBan);
    List<Transaction> giveReportByType(UUID creditAccountId, TransactionType type);
    List<Transaction> giveIntervalReportBySum(UUID creditAccountId, BigDecimal minSum, BigDecimal maxSum);
    File saveReport(List<Transaction> transactions);
}
