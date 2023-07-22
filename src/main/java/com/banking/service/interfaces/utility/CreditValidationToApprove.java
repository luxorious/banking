package com.banking.service.interfaces.utility;

import com.banking.entity.pojo.CreditData;

/**
 * This interface defines the contract for credit approval validation.
 */
public interface CreditValidationToApprove {
    /**
     * Validates whether a credit can be approved based on the provided CreditData.
     *
     * @param creditData The credit data to be validated for approval.
     * @return True if the credit is approved, otherwise false.
     */
    boolean approveCredit(CreditData creditData);
}
