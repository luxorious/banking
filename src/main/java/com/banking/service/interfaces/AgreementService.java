package com.banking.service.interfaces;

import com.banking.entity.Agreement;

import java.util.List;

public interface AgreementService {

    Agreement createAgreement(Agreement agreement);

    List<Agreement> findAgreementByInterestRate(Double interestRate);

}
