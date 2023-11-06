package com.banking.service.component.interfaces;

import com.banking.entity.Account;
import com.banking.entity.Transaction;
import com.banking.entity.pojo.PaymentData;
import com.banking.exception.BadAccountDataException;

import java.util.UUID;

/**
 * The PaymentComponent interface provides methods for handling online payment transactions.
 */
public interface PaymentComponent {

    /**
     * Validates the account for making an online payment transaction.
     *
     * @param id          The ID of the account.
     * @param paymentData The payment data containing transaction details.
     * @return The validated account for payment.
     * @throws BadAccountDataException If the account cannot make the payment.
     */
    Account accountValidation(UUID id, PaymentData paymentData) throws BadAccountDataException;

    /**
     * Processes the online payment transaction.
     *
     * @param id          The ID of the account.
     * @param paymentData The payment data containing transaction details.
     * @throws BadAccountDataException If the account cannot make the payment.
     */
    void pay(UUID id, PaymentData paymentData) throws BadAccountDataException;

    /**
     * Creates a transaction entity for the online payment.
     *
     * @param sender      The account initiating the payment.
     * @param paymentData The payment data containing transaction details.
     * @return The transaction entity.
     */
    Transaction createTransaction(Account sender, PaymentData paymentData);

}
