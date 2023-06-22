package com.banking.businesslogic.interfaces;

import com.banking.entity.Account;
import com.banking.entity.PaymentData;
import com.banking.exception.BadAccountData;

import java.math.BigDecimal;
import java.util.UUID;

public interface PaymentComponent {

    Account accountValidation(UUID id, PaymentData paymentData) throws BadAccountData;

    void pay(UUID id, PaymentData paymentData) throws BadAccountData;

}
