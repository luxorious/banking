package com.banking.service.component.implementation;

import com.banking.service.component.interfaces.PaymentComponent;
import com.banking.entity.Account;
import com.banking.entity.pojo.PaymentData;
import com.banking.entity.Transaction;
import com.banking.entity.entityenumerations.AccountStatus;
import com.banking.exception.BadAccountData;
import com.banking.repository.AccountRepository;
import com.banking.repository.TransactionRepository;
import com.banking.service.interfaces.utility.GetEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Component
public class OnlinePayComponentImpl implements PaymentComponent {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final GetEntity<Account> getAccount;

    @Override
    public Account accountValidation(UUID id, PaymentData paymentData) {
        Account accountFromDB = getAccount.getEntity(accountRepository.findAccountById(id));
        if (accountFromDB.getBalance() != null && accountFromDB.getStatus() == AccountStatus.ACTIVE &&
                accountFromDB.getBalance().compareTo(paymentData.getAmount()) >= 0) {
            return accountFromDB;
        } else {
            throw new BadAccountData("account can`t pay");
        }
    }

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
