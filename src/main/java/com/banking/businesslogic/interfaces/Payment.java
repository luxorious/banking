package com.banking.businesslogic.interfaces;

import com.banking.entity.Account;

import java.math.BigDecimal;
import java.util.UUID;

public interface Payment {

    Account accountValidation(UUID id);

    void pay(UUID id, BigDecimal sum);

}
