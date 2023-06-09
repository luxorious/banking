package com.banking.service.interfaces;

import com.banking.entity.Account;
import com.banking.entity.entityenumerations.AccountStatus;
import com.banking.entity.entityenumerations.AccountType;
import com.banking.entity.entityenumerations.CurrencyCode;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public interface AccountService {

    Account save(Account account);
    Account createAccount(Account account);

    List<Account> findAllActive();

    Account findAccountById(UUID uuid);

    List<Account> findAccountsByName(String name);

    List<Account> findAccountsByStatus(AccountStatus status);

    List<Account> findAccountsByType(AccountType type);

    List<Account> findAccountsByCurrencyCode(CurrencyCode currencyCode);

    List<Account> findAccountsByCreatedAt(Timestamp dateCreation);

    List<Account> findAccountsByUpdatedAt(Timestamp dateUpdate);

    Boolean updateAccountById(UUID id, Account accountFromDB);

    void updateStatusById(UUID id, AccountStatus status);

    Account deleteAccountById(UUID id);

    List<Account> deleteAccountsByStatus(AccountStatus status);

    Account restoreById(UUID id);
    Account findAccountByIdAndCurrencyCode(UUID id, CurrencyCode currencyCode);

    List<Account> restoreAll();

    Account findAccountByIban(String iban);

    List<Account> findAllDeleted();

    List<Account> findAllAccountsForAdmin();

}
