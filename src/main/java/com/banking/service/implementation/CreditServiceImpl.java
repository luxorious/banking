package com.banking.service.implementation;

import com.banking.entity.Bank;
import com.banking.entity.Credit;
import com.banking.entity.entityenumerations.CreditStatus;
import com.banking.entity.pojo.CreditData;
import com.banking.exception.BadAccountDataException;
import com.banking.repository.BankRepository;
import com.banking.repository.CreditRepository;
import com.banking.service.interfaces.CreditService;
import com.banking.service.interfaces.utility.CreditValidationToApprove;
import com.banking.service.interfaces.utility.ValidatorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreditServiceImpl implements CreditService {

    private final CreditRepository creditRepository;
    private final ValidatorService<Credit> entityValidation;
    private final BankRepository bankRepository;
    private final CreditValidationToApprove creditApprove;

    @Value("${bankUuid}")
    private UUID bankId;

    @Override
    public Boolean getCredit(CreditData creditData) {
        return creditApprove.approveCredit(creditData);
    }

    @Override
    public Credit createCredit(CreditData creditData, UUID clientId) {
        if (Boolean.TRUE.equals(getCredit(creditData))) {
            Credit credit = new Credit();
            credit.setClientId(clientId);
            credit.setCreditStatus(CreditStatus.ACTIVE);
            credit.setSumOfCredit(creditData.getSumOfCredit());
            credit.setNumberOfMonth(creditData.getPaymentsNumber());
            credit.setPaymentPerMonth(creditData.getPaymentPerMonth());
            credit.setCreditType(creditData.getType());
            credit.setCurrencyCode(creditData.getCurrencyCode());

            Bank bank = bankRepository.getReferenceById(bankId);
            bank.setBalance(bank.getBalance().subtract(credit.getSumOfCredit()));
            bankRepository.save(bank);

            return creditRepository.save(credit);
        } else {
            throw new BadAccountDataException("you cannot apply for a loan");
        }
    }

    @Override
    public List<Credit> findAllCreditsByClientId(UUID clientId) {
        return entityValidation.checkList(creditRepository.findAllByClientId(clientId));
    }

    @Override
    public List<Credit> findAllActiveByClientId(UUID clientId) {
        return entityValidation.checkList(creditRepository
                .findAllByCreditStatusAndClientId(CreditStatus.ACTIVE, clientId));
    }

    @Override
    public List<Credit> findAll() {
        return entityValidation.checkList(creditRepository.findAll());
    }

    @Override
    public List<Credit> findAllActive() {
        return entityValidation.checkList(creditRepository.findAllByCreditStatus(CreditStatus.ACTIVE));
    }

    @Override
    public Credit save(Credit credit) {
        return creditRepository.save(credit);
    }
}
