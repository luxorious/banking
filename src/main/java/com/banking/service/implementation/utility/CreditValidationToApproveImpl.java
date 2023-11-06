package com.banking.service.implementation.utility;

import com.banking.entity.Client;
import com.banking.entity.Credit;
import com.banking.entity.Transaction;
import com.banking.entity.entityenumerations.ClientStatus;
import com.banking.entity.entityenumerations.CreditStatus;
import com.banking.entity.entityenumerations.TransactionType;
import com.banking.entity.pojo.CreditData;
import com.banking.repository.CreditRepository;
import com.banking.service.interfaces.ClientService;
import com.banking.service.interfaces.TransactionService;
import com.banking.service.interfaces.utility.CreditValidationToApprove;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * An implementation of the {@link CreditValidationToApprove} interface that performs credit validation checks for approval.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class CreditValidationToApproveImpl implements CreditValidationToApprove {

    private final TransactionService transactionService;
    private final ClientService clientService;
    private final CreditRepository creditRepository;

    /**
     * Check if the total sum of transactions for the credit exceeds the requested credit sum.
     *
     * @param creditData The credit data containing credit account ID and sum.
     * @return True if the total sum of transactions exceeds the credit sum, false otherwise.
     */
    private boolean getTotalSumForCredit(CreditData creditData) {
        List<Transaction> transactions =
                transactionService.findTransactionsByCreditAccountIdAndTypeAndCreatedAtBetween(
                        creditData, TransactionType.WIRE_TRANSFER);
        BigDecimal totalSum = BigDecimal.valueOf(0.0);
        for (Transaction transaction : transactions) {
            totalSum = totalSum.add(transaction.getAmount());
        }
        return totalSum.compareTo(creditData.getSumOfCredit()) > 0;
    }

    /**
     * Check if the client status is active or VIP.
     *
     * @param client The client whose status is to be checked.
     * @return True if the client status is active or VIP, false otherwise.
     */
    private boolean checkClientStatus(Client client) {
        return client.getStatus().equals(ClientStatus.ACTIVE) ||
                client.getStatus().equals(ClientStatus.VIP);
    }

    /**
     * Check if the client's credit history contains no credits transferred to collectors.
     *
     * @param credits The list of credits associated with the client.
     * @return True if the client's credit history has no credits transferred to collectors, false otherwise.
     */
    private boolean checkClientsCreditHistory(List<Credit> credits) {
        for (Credit credit : credits) {
            if (credit.getCreditStatus().equals(CreditStatus.TRANSFERRED_TO_COLLECTORS)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Build a list of validation checks based on the provided credit data.
     *
     * @param creditData The credit data containing credit account ID and sum.
     * @return The list of validation checks as booleans.
     */
    private List<Boolean> buildValidationChecks(CreditData creditData) {
        List<Boolean> checkList = new ArrayList<>();
        checkList.add(getTotalSumForCredit(creditData));
        checkList.add(checkClientStatus(clientService.findById(creditData.getId())));
        checkList.add(checkClientsCreditHistory(creditRepository.findAllByClientId(creditData.getId())));
        return checkList;
    }

    /**
     * Approve the credit based on the provided credit data and validation checks.
     *
     * @param creditData The credit data containing credit account ID and sum.
     * @return True if all validation checks pass and the credit is approved, false otherwise.
     */
    @Override
    public boolean approveCredit(CreditData creditData) {
        List<Boolean> validations = buildValidationChecks(creditData);
        for (Boolean validation : validations) {
            if (Boolean.FALSE.equals(validation)) {
                return false;
            }
        }
        return true;
    }
}
