package com.banking.service.component.interfaces;

import com.banking.entity.Account;
import com.banking.entity.Transaction;
import com.banking.entity.pojo.PaymentData;
import com.banking.exception.BadAccountDataException;

import java.util.UUID;

public interface PaymentComponent {

    Account accountValidation(UUID id, PaymentData paymentData) throws BadAccountDataException;

    void pay(UUID id, PaymentData paymentData) throws BadAccountDataException;

    Transaction createTransaction(Account sender, PaymentData paymentData);

}
