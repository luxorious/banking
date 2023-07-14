package com.banking.schedule;

import com.banking.entity.*;
import com.banking.entity.entityenumerations.ClientStatus;
import com.banking.entity.entityenumerations.CreditStatus;
import com.banking.entity.entityenumerations.TransactionType;
import com.banking.repository.BankRepository;
import com.banking.service.mailservice.MailSender;
import com.banking.service.interfaces.AccountService;
import com.banking.service.interfaces.ClientService;
import com.banking.service.interfaces.CreditService;
import com.banking.service.interfaces.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class SchedulePaymentImpl implements SchedulePayment {

    private final CreditService creditService;
    private final ClientService clientService;
    private final AccountService accountService;
    private final TransactionService transactionService;
    private final MailSender mailSender;
    private final BankRepository bankRepository;

    @Value(value = "${schedule.transactionDescription}")
    private String transactionDescription;
    @Value(value = "${bankIban}")
    private String bankIban;
    @Value("${bankUuid}")
    private UUID bankUuid;
    @Value(value = "${mailSander.subject}")
    private String subject;
    @Value(value = "${mailSander.text}")
    private String text;


    @Override
    @Scheduled(cron = "${schedule.cronMonthlyPayment}")
    @Transactional
    public void monthlyPayment() {
        List<Credit> credits = creditService.findAllActive();
        Bank bank = bankRepository.getReferenceById(bankUuid);

        for (Credit credit : credits) {
            Client client = clientService.findById(credit.getClientId());
            Account account = accountService.findAccountByIdAndCurrencyCode(credit.getId(), credit.getCurrencyCode());
            Transaction transaction = initialTransaction(credit.getPaymentPerMonth(), account);

            if (account.getBalance().compareTo(credit.getPaymentPerMonth()) >= 0) {
                if (isLastPayment(credit)) {
                    BigDecimal payment = lastPayment(account, credit);
                    bank.setBalance(bank.getBalance().add(payment));
                    continue;
                }
                account.setBalance(account.getBalance().subtract(credit.getPaymentPerMonth()));
                saveAll(account, credit, transaction);

            } else {
                client.setStatus(ClientStatus.BLACKLISTED);
                clientService.save(client);
                credit.setCreditStatus(CreditStatus.TRANSFERRED_TO_COLLECTORS);
                creditService.save(credit);
            }
        }
        bankRepository.save(bank);
    }

    private BigDecimal lastPayment(Account account, Credit credit) {
        BigDecimal payment = credit.getSumOfCredit();
        account.setBalance(account.getBalance().subtract(payment));
        credit.setSumOfCredit(BigDecimal.valueOf(0));
        credit.setCreditStatus(CreditStatus.PAID);
        Transaction transaction = initialTransaction(payment, account);
        saveAll(account, credit, transaction);

        return payment;
    }

    private boolean isLastPayment(Credit credit) {
        return credit.getSumOfCredit().compareTo(credit.getPaymentPerMonth()) <= 0;
    }

    private Transaction initialTransaction(BigDecimal payment, Account account) {
        Transaction transaction = new Transaction();
        transaction.setDescription(transactionDescription);
        transaction.setType(TransactionType.PAYMENT);
        transaction.setSenderIBan(account.getIBan());
        transaction.setAmount(payment);
        transaction.setCreditAccountId(account.getId());
        transaction.setReceiverIban(bankIban);

        return transaction;
    }

    private void saveAll(Account account, Credit credit, Transaction transaction) {
        accountService.save(account);
        creditService.save(credit);
        transactionService.save(transaction);
    }

    @Override
    @Scheduled(cron = "${schedule.cronNotification}")
    public void notification() {
        List<Credit> credits = creditService.findAllActive();
        for (Credit credit : credits) {
            Client client = clientService.findById(credit.getClientId());
            String eMail = client.getEmail();
            sendNotification(eMail);
        }
    }

    private void sendNotification(String eMail) {
        mailSender.send(eMail, text, subject);
    }
}
