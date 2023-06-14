package com.banking.service.interfaces;

import com.banking.entity.Account;
import com.banking.entity.entityEnumerations.AccountStatus;
import com.banking.entity.entityEnumerations.AccountType;
import com.banking.entity.entityEnumerations.CurrencyCode;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountService {
    Account createAccount(Account account);

    Optional<Account> findAccountById(UUID uuid);

    List<Account> findAccountsByName(String name);

    List<Account> findAccountsByStatus(AccountStatus status);

    List<Account> findAccountsByType(AccountType type);

    List<Account> findAccountsByCurrencyCode(CurrencyCode currencyCode);

    List<Account> findAccountsByCreatedAt(Timestamp dateCreation);

    List<Account> findAccountsByUpdatedAt(Timestamp dateUpdate);

    Account updateAccountByClientId(UUID clientId, Account account);
    Account updateAccountById(UUID id);
    List<Account> updateAccountsByStatus(AccountStatus status);

    Account deleteById(UUID id);

    Account deleteAccountsByName(String name);
    List<Account> deleteAccountsByStatus(AccountStatus status);

}
