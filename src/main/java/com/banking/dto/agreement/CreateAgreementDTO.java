package com.banking.dto.agreement;

import com.banking.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateAgreementDTO {
    private UUID accountId;
    private Account account;
    private UUID productId;
    private String interestRate;
    private String status;
    private String sum;
}
