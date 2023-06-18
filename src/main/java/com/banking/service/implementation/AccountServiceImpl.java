package com.banking.service.implementation;

import com.banking.entity.Account;
import com.banking.entity.entityEnumerations.AccountStatus;
import com.banking.entity.entityEnumerations.AccountType;
import com.banking.entity.entityEnumerations.CurrencyCode;
import com.banking.entity.entityEnumerations.DeletedStatus;
import com.banking.repository.AccountRepository;
import com.banking.service.interfaces.AccountService;
import com.banking.service.interfaces.utility.Converter;
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
    private final Converter<Account> accountConverter;

    @Override
    public Account createAccount(Account account) {
        log.info("creating account " + account);
        return accountRepository.save(account);
    }

    @Override
    public List<Account> findAll() {
        log.info("get all active accounts");
        return accountRepository.findAccountsByDeletedStatus(DeletedStatus.ACTIVE);
    }

    @Override
    public List<Account> showAllDeleted(){
        return accountRepository.findAccountsByDeletedStatus(DeletedStatus.DELETED);
    }

    @Override
    public List<Account> showAllAccountsForAdmin(){
        return accountRepository.findAll();
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
    public Boolean updateAccountById(UUID id, Account accountFromFE) {
        Optional<Account> accountFromDB = accountRepository.findAccountById(id);
        if (accountFromDB.isPresent()){
            Account account =accountFromDB.get();
            Account accountToUpdate = accountConverter.convertFields(account, accountFromFE);
            accountRepository.save(accountToUpdate);
            log.info("Account updated successfully for client ID: " + id);
            return true;
        } else {
            log.info("Account with client ID: " + id + " not found");
            return false;
        }
    }


    @Override
    public void updateStatusById(UUID id, AccountStatus status){
        Optional<Account> accountFromDB = accountRepository.findAccountById(id);
        if (accountFromDB.isPresent()){
            accountFromDB.get().setStatus(status);
            accountRepository.save(accountFromDB.get());
            log.info("Account status successfully updated: " + id);
        } else {
            log.info("Account with client ID: " + id + " not found");
        }
    }

    @Override
    public Account deleteAccountById(UUID id){
        Optional<Account> deletedAccount = accountRepository.findAccountById(id);
        if (deletedAccount.isPresent()){
            Account account = deletedAccount.get();
            account.setDeletedStatus(DeletedStatus.DELETED);
            accountRepository.save(account);
            log.info("delete account where UUID - " + id);
            return deletedAccount.get();
        } else {
            log.info("Account not found!");
            return new Account();
        }
    }

    @Override
    public List<Account> deleteAccountsByStatus(AccountStatus status) {
        List<Account> accounts = accountRepository.findAccountsByStatus(status);
        accounts.forEach(account -> account.setDeletedStatus(DeletedStatus.DELETED));
        log.info("deleting accounts where Account Status - " + status);
        return accounts;
    }

    @Override
    public Account restoreById(UUID id) {
        Optional<Account> account = accountRepository.findAccountById(id);
        if (account.isPresent()){
            Account accountToRestore = account.get();
            accountToRestore.setDeletedStatus(DeletedStatus.ACTIVE);
            accountRepository.save(accountToRestore);
            log.info("account restored");
            return accountToRestore;
        } else {
            log.error("account not found");
            return new Account();
        }
    }

    @Override
    public List<Account> restoreAll() {
        List<Account> accounts = accountRepository.findAccountsByDeletedStatus(DeletedStatus.DELETED);
        accounts.forEach(account -> account.setDeletedStatus(DeletedStatus.ACTIVE));
        accountRepository.saveAll(accounts);
        return accounts;
    }

    //чи залишити цей метод тут, чи створити інтерфейс з дженеріками
    //і різні реалізації для кожної сутності(або одну параметризовану)
    //надалі планую використовувати його для відображення аккаунтів
    // або інших сутностей які не є заблоковані, чи це зайве?
    private boolean checkDeletedStatus(Account account){
        return account.getDeletedStatus() == DeletedStatus.ACTIVE;
    }


}
