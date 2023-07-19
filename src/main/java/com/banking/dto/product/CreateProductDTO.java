package com.banking.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateProductDTO {

    private String name;
    private String status;
    private String currencyCode;
    private String interestRate;
    private String limit;
    private String deletedStatus;

}
