package com.banking.dto.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateTransactionDTO {

    private String receiverIban;
    private String type;
    private String amount;
    private String description;
    private String senderIBan;

}
