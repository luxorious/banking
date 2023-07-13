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

    Account createAccount(Account account, UUID clientId);

    List<Account> findAllActiveById(UUID id);

    Account findAccountById(UUID id);

    List<Account> findAccountsByIdAndName(UUID id, String name);

    List<Account> findAccountsByIdAndStatus(UUID id, AccountStatus status);

    List<Account> findAccountsByIdAndType(UUID id, AccountType type);

    List<Account> findAccountsByIdAndCurrencyCode(UUID id, CurrencyCode currencyCode);

    List<Account> findAccountsByCreatedAt(Timestamp dateCreation);

    List<Account> findAccountsByUpdatedAt(Timestamp dateUpdate);

    Boolean updateAccountById(UUID id, Account accountFromDB);

    void updateStatusById(UUID id, AccountStatus status);

    Account deleteAccountById(UUID id);

    List<Account> deleteAccountsByIdAndStatus(UUID id, AccountStatus status);

    Account restoreById(UUID id);
    Account findAccountByIdAndCurrencyCode(UUID id, CurrencyCode currencyCode);

    List<Account> restoreAllById(UUID id);

    Account findAccountByIban(String iban);

    List<Account> findAllDeleted();

    List<Account> findAllAccountsForAdmin();

}
