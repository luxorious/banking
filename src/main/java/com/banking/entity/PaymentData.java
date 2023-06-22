package com.banking.entity;

import com.banking.entity.entityEnumerations.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class PaymentData {

    private TransactionType type;

    private BigDecimal amount;

    private String description;

    private String iBan;
}
