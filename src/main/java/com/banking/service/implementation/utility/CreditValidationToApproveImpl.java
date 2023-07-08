package com.banking.service.implementation.utility;

import com.banking.entity.Client;
import com.banking.entity.Credit;
import com.banking.entity.Transaction;
import com.banking.entity.entityenumerations.ClientStatus;
import com.banking.entity.entityenumerations.CreditStatus;
import com.banking.entity.entityenumerations.TransactionType;
import com.banking.entity.pojo.CreditData;
import com.banking.service.interfaces.ClientService;
import com.banking.service.interfaces.CreditService;
import com.banking.service.interfaces.TransactionService;
import com.banking.service.interfaces.utility.CreditValidationToApprove;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreditValidationToApproveImpl implements CreditValidationToApprove {

    private final TransactionService transactionService;
    private final ClientService clientService;
    private final CreditService creditService;

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

    private boolean checkClientStatus(Client client) {
        return client.getStatus().equals(ClientStatus.ACTIVE) ||
                client.getStatus().equals(ClientStatus.VIP);
    }

    private boolean checkClientsCreditHistory(List<Credit> credits) {
        for (Credit credit : credits) {
            if (credit.getCreditStatus().equals(CreditStatus.TRANSFERRED_TO_COLLECTORS)) {
                return false;
            }
        }
        return true;
    }

    private List<Boolean> buildValidationChecks(CreditData creditData) {
        List<Boolean> checkList = new ArrayList<>();
        checkList.add(getTotalSumForCredit(creditData));
        checkList.add(checkClientStatus(clientService.findById(creditData.getId())));
        checkList.add(checkClientsCreditHistory(creditService.findAllCreditsByClientId(creditData.getId())));
        return checkList;
    }

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
