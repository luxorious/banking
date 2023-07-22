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

/**
 * Service implementation for managing clients.
 */
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

    /**
     * Approves a credit based on the provided CreditData.
     *
     * @param creditData The CreditData object containing credit-related information.
     * @return true if the credit is approved, false otherwise.
     */
    @Override
    public Boolean getCredit(CreditData creditData) {
        return creditApprove.approveCredit(creditData);
    }

    /**
     * Creates a new credit for the specified client with the provided CreditData.
     *
     * @param creditData The CreditData object containing credit-related information.
     * @param clientId The UUID of the client applying for the credit.
     * @return The created and saved Credit object.
     * @throws BadAccountDataException if the credit is not approved.
     */
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

    /**
     * Finds all credits associated with the specified client.
     *
     * @param clientId The UUID of the client to find credits for.
     * @return List of credits associated with the specified client.
     */
    @Override
    public List<Credit> findAllCreditsByClientId(UUID clientId) {
        return entityValidation.checkList(creditRepository.findAllByClientId(clientId));
    }

    /**
     * Finds all active credits associated with the specified client.
     *
     * @param clientId The UUID of the client to find active credits for.
     * @return List of active credits associated with the specified client.
     */
    @Override
    public List<Credit> findAllActiveByClientId(UUID clientId) {
        return entityValidation.checkList(creditRepository
                .findAllByCreditStatusAndClientId(CreditStatus.ACTIVE, clientId));
    }

    /**
     * Finds all credits in the repository.
     *
     * @return List of all credits.
     */
    @Override
    public List<Credit> findAll() {
        return entityValidation.checkList(creditRepository.findAll());
    }

    /**
     * Finds all active credits in the repository.
     *
     * @return List of all active credits.
     */
    @Override
    public List<Credit> findAllActive() {
        return entityValidation.checkList(creditRepository.findAllByCreditStatus(CreditStatus.ACTIVE));
    }

    /**
     * Saves a credit to the repository.
     *
     * @param credit The credit to be saved.
     * @return The saved credit.
     */
    @Override
    public Credit save(Credit credit) {
        return creditRepository.save(credit);
    }
}
