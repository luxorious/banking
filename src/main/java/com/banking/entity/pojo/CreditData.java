package com.banking.entity.pojo;

import com.banking.entity.entityenumerations.CreditType;
import com.banking.entity.entityenumerations.CurrencyCode;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.UUID;

/**
 * A POJO class representing credit data used for creating and processing credits in the banking application.
 */
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

    /**
     * Get a loan start date based on the current date and number of payments.
     *
     * @return A timestamp representing the start date for credit calculation.
     * @throws DateTimeException if the number of payments is zero or less or equal to zero.
     */
    public Timestamp getStartDate() {
        if (paymentsNumber != null && paymentsNumber > 0) {
            LocalDate currentDate = LocalDate.now();
            LocalDate startDate = currentDate.minusMonths(paymentsNumber);
            return Timestamp.valueOf(startDate.atStartOfDay());
        }
        throw new DateTimeException("wrong format date!");
    }

    /**
     * Calculate and set the monthly payment amount for the credit.
     *
     * @return The calculated monthly payment amount.
     * @throws ArithmeticException if any of the required data (sumOfCredit, paymentsNumber) is null.
     */
    @PostConstruct
    public BigDecimal getMonthlyPayment() {
        if (paymentPerMonth != null && sumOfCredit != null && paymentsNumber != null) {
            paymentPerMonth = sumOfCredit.divide(new BigDecimal(paymentsNumber), 2, RoundingMode.HALF_UP);
            return paymentPerMonth;
        }
        throw new ArithmeticException("incorrect data");
    }
}
