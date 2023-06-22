package com.banking.businesslogic.implementation;

import com.banking.businesslogic.interfaces.PaymentComponent;
import com.banking.entity.Account;
import com.banking.entity.PaymentData;
import com.banking.entity.Transaction;
import com.banking.entity.entityEnumerations.AccountStatus;
import com.banking.exception.BadAccountData;
import com.banking.repository.AccountRepository;
import com.banking.service.interfaces.AccountService;
import com.banking.service.interfaces.utility.GetEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
//@Component
public class OnlinePayComponentImpl implements PaymentComponent {
    private final AccountRepository accountRepository;
    private final AccountService accountService;
    private final Transaction transaction;
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
        Optional<Account> accountReceiver = accountRepository.getAccountByIBan(paymentData.getIBan());
        if (accountReceiver.isPresent()) {
            Account receiver = accountReceiver.get();
            receiver.setBalance(receiver.getBalance().add(paymentData.getAmount()));
            accountRepository.save(receiver);
            accountRepository.save(sender);
            saveTransaction(sender, receiver, paymentData.getAmount());
        } else {
            accountRepository.save(sender);
            saveTransaction(sender, paymentData);
            log.info("payment successful");
        }
        log.info("payment successful");
    }


    public void saveTransaction(Account sender, Account receiver, BigDecimal sum) {
        Transaction transaction = new Transaction();
        transaction.setDebitAccountId(sender.getId());
        transaction.setAmount(sum);
        transaction.setCreditAccountId(receiver.getId());
        //dosomethig
    }

    public void saveTransaction(Account sender, PaymentData paymentData) {
        Transaction transaction = new Transaction();
        transaction.setAmount(paymentData.getAmount());
        transaction.setIBan(paymentData.getIBan());
        transaction.setDebitAccountId(null);
        //dosomethig
    }
}
