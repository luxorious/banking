package com.banking.entity.pojo;

import com.banking.entity.entityenumerations.CreditType;
import com.banking.entity.entityenumerations.CurrencyCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.DateTimeException;
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
        if (paymentsNumber != null && paymentsNumber > 0) {
            LocalDate currentDate = LocalDate.now();
            LocalDate startDate = currentDate.minusMonths(paymentsNumber);
            return Timestamp.valueOf(startDate.atStartOfDay());
        }
        throw new DateTimeException("wrong format date!");
    }


    public BigDecimal getMonthlyPayment() {
        if (paymentPerMonth != null && sumOfCredit != null && paymentsNumber != null) {
            paymentPerMonth = sumOfCredit.divide(new BigDecimal(paymentsNumber), 2, RoundingMode.HALF_UP);
            return paymentPerMonth;
        }
        throw new ArithmeticException("incorrect data");
    }
}
