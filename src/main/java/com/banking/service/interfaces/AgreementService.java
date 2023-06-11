package com.banking.service.interfaces;

import com.banking.entity.Agreement;

import java.util.List;

public interface AgreementService {

    Agreement createAgreement(Agreement agreement);

//    Optional<Agreement> findById(UUID uuid);
//
    List<Agreement> findAgreementByInterestRate(Double interestRate);

}
