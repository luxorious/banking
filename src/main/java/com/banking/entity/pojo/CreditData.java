package com.banking.entity.pojo;

import com.banking.entity.entityenumerations.CreditType;
import com.banking.entity.entityenumerations.CurrencyCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditData {

    private BigDecimal sumOfCredit;

    private CurrencyCode currencyCode;

    private Integer paymentsNumber;

    private String description;

    private UUID id;

    private BigDecimal paymentPerMonth;

    private CreditType type;

    public Timestamp getStartDate() {
        LocalDate currentDate = LocalDate.now();
        LocalDate startDate = currentDate.minusMonths(paymentsNumber);
        return Timestamp.valueOf(startDate.atStartOfDay());
    }

    public BigDecimal getMonthlyPayment() {
        //null pointer
        paymentPerMonth = sumOfCredit.divide(new BigDecimal(paymentsNumber), RoundingMode.HALF_UP);
        return paymentPerMonth;
    }
}
