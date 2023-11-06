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

/**
 * The SchedulePaymentImpl class is a component responsible for scheduled tasks related to payments and notifications.
 * It performs monthly credit payments and sends payment notifications to active credit clients.
 */
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

    /**
     * Properties read from configuration
     */
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

    /**
     * Performs the monthly credit payment for active credits.
     * <p>
     * This method is scheduled to run periodically according to the specified cron expression in the configuration.
     * It fetches all active credits and processes their monthly payments. If a credit account has enough balance to cover
     * the monthly payment, the payment is deducted from the account balance. Otherwise, the client associated with the
     * credit account is blacklisted, and the credit status is changed to TRANSFERRED_TO_COLLECTORS.
     * <p>
     * The method also handles the last payment of a credit account. If the remaining credit amount is less than the monthly
     * payment, the method deducts the remaining amount from the account balance and updates the credit status to "PAID".
     *
     * @implNote This method requires transactions to be managed to ensure the consistency of credit payments and account
     * balance updates. Therefore, the @Transactional annotation is used to wrap the entire method in a transaction.
     */
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

    /**
     * Handles the last payment of a credit account.
     * <p>
     * When the remaining credit amount is less than the monthly payment, this method deducts the remaining amount from
     * the account balance and updates the credit status to "PAID". It also creates and saves a transaction record for the last
     * payment.
     *
     * @param account The account associated with the credit.
     * @param credit  The credit account for which the last payment is being processed.
     * @return The last payment amount that was deducted from the account balance.
     */
    private BigDecimal lastPayment(Account account, Credit credit) {
        BigDecimal payment = credit.getSumOfCredit();
        account.setBalance(account.getBalance().subtract(payment));
        credit.setSumOfCredit(BigDecimal.valueOf(0));
        credit.setCreditStatus(CreditStatus.PAID);
        Transaction transaction = initialTransaction(payment, account);
        saveAll(account, credit, transaction);

        return payment;
    }

    /**
     * Checks if the current payment is the last payment for the credit account.
     * <p>
     * This method compares the remaining credit amount (sumOfCredit) with the monthly payment (paymentPerMonth)
     * to determine if the current payment is the last payment.
     *
     * @param credit The credit account for which the last payment is being checked.
     * @return True if the current payment is the last payment, false otherwise.
     */
    private boolean isLastPayment(Credit credit) {
        return credit.getSumOfCredit().compareTo(credit.getPaymentPerMonth()) <= 0;
    }

    /**
     * Creates an initial transaction for the monthly payment of a credit account.
     * <p>
     * This method creates a new Transaction object with the necessary details for processing
     * the monthly payment of a credit account.
     *
     * @param payment The amount of the monthly payment.
     * @param account The account from which the payment is made.
     * @return The Transaction object representing the initial payment transaction.
     */
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

    /**
     * Save all the entities related to the monthly payment process.
     * <p>
     * This method is responsible for saving the updated Account, Credit, and Transaction entities
     * after processing the monthly payment of a credit account.
     *
     * @param account     The updated Account entity after deducting the payment amount.
     * @param credit      The updated Credit entity after deducting the payment amount or setting it to PAID if it's the last payment.
     * @param transaction The Transaction entity representing the payment transaction.
     */
    private void saveAll(Account account, Credit credit, Transaction transaction) {
        accountService.save(account);
        creditService.save(credit);
        transactionService.save(transaction);
    }

    /**
     * Send notification emails to clients with active credits.
     * <p>
     * This method is responsible for sending notification emails to clients who have active credits.
     * It retrieves a list of active credits from the CreditService and then sends a notification email
     * to each client's email address using the sendNotification method.
     */
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

    /**
     * Send a notification email to the specified email address.
     * <p>
     * This method sends a notification email to the given email address using the MailSender service.
     *
     * @param eMail The email address to which the notification will be sent.
     */
    private void sendNotification(String eMail) {
        mailSender.send(eMail, text, subject);
    }
}
