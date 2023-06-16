package com.banking.service.interfaces;

import com.banking.entity.Account;
import com.banking.entity.entityEnumerations.AccountStatus;
import com.banking.entity.entityEnumerations.AccountType;
import com.banking.entity.entityEnumerations.CurrencyCode;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountService {
    Account createAccount(Account account);

    List<Account> findAll();

    Optional<Account> findAccountById(UUID uuid);

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
    List<Account> restoreAll();

}
