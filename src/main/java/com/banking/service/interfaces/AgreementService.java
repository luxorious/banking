package com.banking.service.interfaces;

import com.banking.entity.Agreement;
import com.banking.entity.entityEnumerations.AgreementStatus;
import com.banking.repository.AgreementRepository;

import java.util.List;
import java.util.UUID;

public interface AgreementService {

    Agreement createAgreement(Agreement agreement);

    List<Agreement> findAgreementByInterestRate(Double interestRate);

    Agreement editAgreement(UUID id, Agreement agreementFE);

    Agreement deleteAgreementById(UUID id);

    List<Agreement> deleteAgreementsByStatus(AgreementStatus status);

    Agreement changeSumById(UUID id, Double newSum);

}
