package com.banking.service.component.interfaces;

import com.banking.entity.Transaction;

import java.io.File;
import java.util.List;

public interface Receipt {
    String create(List<Transaction> transactions);
    File saveReport(List<Transaction> transactions);
}
