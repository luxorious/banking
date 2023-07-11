package com.banking.service.interfaces;

import com.banking.entity.Agreement;
import com.banking.entity.entityenumerations.AgreementStatus;


import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface AgreementService {

    List<Agreement> findAll();
    Agreement save(Agreement agreement);

    Agreement createAgreement(Agreement agreement, UUID accountId);

    List<Agreement> findAgreementByInterestRate(BigDecimal interestRate);

    Agreement editAgreement(UUID id, Agreement agreementFE);

    Agreement deleteAgreementById(UUID id);

    List<Agreement> deleteAgreementsByStatus(AgreementStatus status);

    Agreement changeSumById(UUID id, BigDecimal newSum);

    List<Agreement> showDeleted();
}
