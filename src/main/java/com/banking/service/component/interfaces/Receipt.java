package com.banking.service.component.interfaces;

import com.banking.entity.Transaction;

import java.io.File;
import java.util.List;

/**
 * The Receipt interface provides methods for creating and saving receipts based on a list of transactions.
 */
public interface Receipt {

    /**
     * Creates a receipt based on a list of transactions.
     *
     * @param transactions The list of transactions to include in the receipt.
     * @return The receipt as a string.
     */
    String create(List<Transaction> transactions);

    /**
     * Saves the receipt as a text file based on a list of transactions.
     *
     * @param transactions The list of transactions to include in the receipt.
     * @return The generated file containing the receipt.
     */
    File saveReport(List<Transaction> transactions);
}
