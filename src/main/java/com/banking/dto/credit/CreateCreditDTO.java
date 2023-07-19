package com.banking.dto.credit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateCreditDTO {
    private Double sumOfCredit;
    private Integer numberOfMonth;
    private Double paymentPerMonth;
    private String creditType;
    private String currencyCode;
    private String creditStatus;

}
