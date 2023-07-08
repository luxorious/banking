package com.banking.service.implementation;

import com.banking.entity.Account;
import com.banking.entity.Credit;
import com.banking.entity.entityenumerations.CreditStatus;
import com.banking.entity.pojo.CreditData;
import com.banking.repository.CreditRepository;
import com.banking.service.interfaces.AccountService;
import com.banking.service.interfaces.CreditService;
import com.banking.service.interfaces.utility.CreditValidationToApprove;
import com.banking.service.interfaces.utility.ValidatorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreditServiceImpl implements CreditService {

    private final CreditRepository creditRepository;
    private final ValidatorService<Account> accountValidatorService;
    private final AccountService accountService;
    private final ValidatorService<Credit> entityValidation;

    //циклиская зависимость в schedulePaymentImpl
////    private final CreditValidationToApprove creditApprove;
//
//    @Override
//    public Boolean getCredit(CreditData creditData) {
//        return creditApprove.approveCredit(creditData);
//    }

    @Override
    public List<Credit> findAllCreditsByClientId(UUID clientId) {
        return entityValidation.checkList(creditRepository.findAllByClientId(clientId));
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
