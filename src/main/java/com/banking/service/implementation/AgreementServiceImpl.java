package com.banking.service.implementation;

import com.banking.entity.Agreement;
import com.banking.repository.AgreementRepository;
import com.banking.service.implementation.utility.AgreementConverterImpl;
import com.banking.service.interfaces.AgreementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgreementServiceImpl implements AgreementService {

    private final AgreementRepository agreementRepository;
    private final AgreementConverterImpl agreementConverter;
    @Override
    public Agreement createAgreement(Agreement agreement) {
        return agreementRepository.save(agreement);
    }

    @Override
    public List<Agreement> findAgreementByInterestRate(Double interestRate) {
        return agreementRepository.findAgreementByInterestRate(interestRate);
    }
}
