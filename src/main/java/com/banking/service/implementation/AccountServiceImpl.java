package com.banking.service.implementation;

import com.banking.entity.Account;
import com.banking.entity.entityEnumerations.AccountStatus;
import com.banking.entity.entityEnumerations.AccountType;
import com.banking.entity.entityEnumerations.CurrencyCode;
import com.banking.repository.AccountRepository;
import com.banking.service.interfaces.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public Account createAccount(Account account) {
        log.info("creating account " + account);
        return accountRepository.save(account);
    }

    @Override
    public Optional<Account> findAccountById(UUID uuid) {
        log.info("find account if exist with UUID - " + uuid);
        return accountRepository.findAccountById(uuid);
    }

    @Override
    public List<Account> findAccountsByName(String name) {
        log.info("find all accounts where name - " + name);
        return accountRepository.findAccountsByName(name);
    }

    @Override
    public List<Account> findAccountsByStatus(AccountStatus status) {
        log.info("find all accounts where status - " + status);
        return accountRepository.findAccountsByStatus(status);
    }

    @Override
    public List<Account> findAccountsByType(AccountType type) {
        log.info("find all accounts where type - " + type);
        return accountRepository.findAccountsByType(type);
    }

    @Override
    public List<Account> findAccountsByCurrencyCode(CurrencyCode currencyCode) {
        log.info("find all accounts where currency code - " + currencyCode);
        return accountRepository.findAccountsByCurrencyCode(currencyCode);
    }

    @Override
    public List<Account> findAccountsByCreatedAt(Timestamp dateCreation) {
        log.info("find all accounts where date of creation - " + dateCreation);
        return accountRepository.findAccountsByCreatedAt(dateCreation);
    }

    @Override
    public List<Account> findAccountsByUpdatedAt(Timestamp dateUpdate) {
        log.info("find all accounts where date of update - " + dateUpdate);
        return accountRepository.findAccountsByUpdatedAt(dateUpdate);
    }

    @Override
    public Account updateAccountByClientId(UUID clientId, Account account) {
        if (accountRepository.updateAccountByClientId(clientId)) {
            log.info("Account updated successfully for client ID: " + clientId);
            return null;
        } else {
            log.info("Account with client ID: " + clientId + " not found");
            return null;
        }
    }

    @Override
    public Account updateAccountById(UUID id) {
        log.info("update account where UUID - " + id);
        return null;
    }

    @Override
    public List<Account> updateAccountsByStatus(AccountStatus status) {
        log.info("update accounts where status - " + status);
        return null;
    }

    @Override
    public Account deleteById(UUID id) {
        log.info("delete account where UUID - " + id);
        return null;
    }

    @Override
    public Account deleteAccountsByName(String name) {
        log.info("delete accounts where name - " + name);
        return null;
    }

    @Override
    public List<Account> deleteAccountsByStatus(AccountStatus status) {
        log.info("delete accounts where Account Status - " + status);
        return null;
    }
}
