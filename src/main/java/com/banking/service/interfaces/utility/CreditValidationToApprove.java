package com.banking.service.interfaces.utility;

import com.banking.entity.pojo.CreditData;

public interface CreditValidationToApprove {
    boolean approveCredit(CreditData creditData);
}
