package com.banking.dto.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountDTO {
    private String id;
    private String name;
    private String type;
    private String status;
    private String balance;
    private String currencyCode;
    private String iBan;
}
