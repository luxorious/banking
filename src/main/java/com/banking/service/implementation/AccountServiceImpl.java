package com.banking.service.implementation;

import com.banking.entity.Account;
import com.banking.entity.entityEnumerations.AccountStatus;
import com.banking.entity.entityEnumerations.AccountType;
import com.banking.entity.entityEnumerations.CurrencyCode;
import com.banking.repository.AccountRepository;
import com.banking.service.interfaces.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Optional<Account> findAccountById(UUID uuid) {
        return accountRepository.findAccountById(uuid);
    }

    @Override
    public Optional<Account> findAccountsByName(String name) {
        return accountRepository.findAccountsByName(name);
    }

    @Override
    public List<Account> findAccountsByStatus(AccountStatus status) {
        return accountRepository.findAccountsByStatus(status);
    }

    @Override
    public List<Account> findAccountsByType(AccountType type) {
        return accountRepository.findAccountsByType(type);
    }

    @Override
    public List<Account> findAccountsByCurrencyCode(CurrencyCode currencyCode) {
        return accountRepository.findAccountsByCurrencyCode(currencyCode);
    }

    @Override
    public List<Account> findAccountsByCreatedAt(Date dateCreation) {
        return accountRepository.findAccountsByCreatedAt(dateCreation);
    }

    @Override
    public List<Account> findAccountsByUpdatedAt(Date dateUpdate) {
        return accountRepository.findAccountsByUpdatedAt(dateUpdate);
    }
}
