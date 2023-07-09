package com.banking.dto.agreement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AgreementDTO {
    private String Id;
    private String accountId;
    private String productId;
    private String interestRate;
    private String status;
    private String sum;
}
