package com.banking.service.component.interfaces;

import com.banking.entity.Account;
import com.banking.entity.pojo.PaymentData;
import com.banking.exception.BadAccountData;

import java.util.UUID;

public interface PaymentComponent {

    Account accountValidation(UUID id, PaymentData paymentData) throws BadAccountData;

    void pay(UUID id, PaymentData paymentData) throws BadAccountData;

    void saveTransaction(Account sender, PaymentData paymentData);
}
