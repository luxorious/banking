package com.banking.service.component.implementation;

import com.banking.service.component.interfaces.PaymentComponent;
import com.banking.entity.Account;
import com.banking.entity.pojo.PaymentData;
import com.banking.entity.Transaction;
import com.banking.entity.entityenumerations.AccountStatus;
import com.banking.exception.BadAccountDataException;
import com.banking.repository.AccountRepository;
import com.banking.repository.TransactionRepository;
import com.banking.service.interfaces.utility.GetEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;
/**
 * Implements the PaymentComponent interface to handle online payment transactions.
 */
@RequiredArgsConstructor
@Slf4j
@Component
public class OnlinePayComponentImpl implements PaymentComponent {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final GetEntity<Account> getAccount;

    /**
     * Validates the account for making an online payment transaction.
     *
     * @param id          The ID of the account.
     * @param paymentData The payment data containing transaction details.
     * @return The validated account for payment.
     * @throws BadAccountDataException If the account cannot make the payment.
     */
    @Override
    public Account accountValidation(UUID id, PaymentData paymentData) {
        Account accountFromDB = getAccount.getEntity(accountRepository.findAccountById(id));
        if (accountFromDB.getBalance() != null && accountFromDB.getStatus() == AccountStatus.ACTIVE &&
                accountFromDB.getBalance().compareTo(paymentData.getAmount()) >= 0) {
            return accountFromDB;
        } else {
            throw new BadAccountDataException("account can`t pay");
        }
    }

    /**
     * Processes the online payment transaction.
     *
     * @param id          The ID of the account.
     * @param paymentData The payment data containing transaction details.
     */
    @Override
    @Transactional
    public void pay(UUID id, PaymentData paymentData) {
        Account sender = accountValidation(id, paymentData);
        sender.setBalance(sender.getBalance().subtract(paymentData.getAmount()));
        Optional<Account> accountReceiver = accountRepository.findByIBan(paymentData.getIBan());
        Account receiver;
        if (accountReceiver.isPresent()) {
            receiver = accountReceiver.get();
            paymentData.setReceiverId(receiver.getId());
            receiver.setBalance(receiver.getBalance().add(paymentData.getAmount()));
            accountRepository.save(receiver);
        }
        Transaction transaction = createTransaction(sender, paymentData);
        transactionRepository.save(transaction);
        log.info("transaction saved");
        accountRepository.save(sender);
        log.info("payment successful");
    }

    /**
     * Creates a transaction entity for the online payment.
     *
     * @param sender      The account initiating the payment.
     * @param paymentData The payment data containing transaction details.
     * @return The transaction entity.
     */
    @Override
    public Transaction createTransaction(Account sender, PaymentData paymentData) {
        Transaction transaction = new Transaction();
        transaction.setCreditAccountId(sender.getId());
        transaction.setAmount(paymentData.getAmount());
        transaction.setReceiverIban(paymentData.getIBan());
        transaction.setSenderIBan(sender.getIBan());
        transaction.setType(paymentData.getType());
        transaction.setDescription(paymentData.getDescription());

        return transaction;
    }
}
