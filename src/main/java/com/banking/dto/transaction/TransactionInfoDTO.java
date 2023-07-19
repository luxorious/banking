package com.banking.dto.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionInfoDTO {
    private String receiverIban;
    private String type;
    private String amount;
    private String description;
}
