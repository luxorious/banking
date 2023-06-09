package com.banking.entity.pojo;

import com.banking.entity.entityenumerations.TransactionType;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@RequiredArgsConstructor
@Data
public class PaymentData {

    private final TransactionType type;

    private final BigDecimal amount;

    private final String description;

    private final String iBan;

    private UUID receiverId;
}
