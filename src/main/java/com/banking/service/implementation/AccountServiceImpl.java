package com.banking.service.implementation;

import com.banking.service.component.interfaces.IBanGeneratorComponent;
import com.banking.entity.Account;
import com.banking.entity.entityenumerations.AccountStatus;
import com.banking.entity.entityenumerations.AccountType;
import com.banking.entity.entityenumerations.CurrencyCode;
import com.banking.entity.entityenumerations.DeletedStatus;
import com.banking.repository.AccountRepository;
import com.banking.service.interfaces.AccountService;
import com.banking.service.interfaces.utility.Converter;
import com.banking.service.interfaces.utility.GetEntity;
import com.banking.service.interfaces.utility.ValidatorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final Converter<Account> accountConverter;
    private final IBanGeneratorComponent iBanGenerator;
    private final GetEntity<Account> getEntity;
    private final ValidatorService<Account> validatorService;

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }


    @Override
    public Account createAccount(Account account, UUID clientId) {
        account.setDeletedStatus(DeletedStatus.ACTIVE);
        account.setIBan(iBanGenerator.generate());
        account.setClientId(clientId);
        log.info("creating account " + account);
        return accountRepository.save(account);
    }

    @Override
    public List<Account> findAllActiveById(UUID id) {
        log.info("get all active accounts");
        return validatorService.checkList(accountRepository.findAccountsByIdAndDeletedStatus(id, DeletedStatus.ACTIVE));
    }

    @Override
    public List<Account> findAllDeleted(){
        return validatorService.checkList(accountRepository.findAccountsByDeletedStatus(DeletedStatus.DELETED));
    }

    @Override
    public List<Account> findAllAccountsForAdmin(){
        return validatorService.checkList(accountRepository.findAll());
    }

    @Override
    public Account findAccountById(UUID uuid) {
        log.info("find account if exist with UUID - " + uuid);
        return getEntity.getEntity(accountRepository.findAccountById(uuid));
    }

    @Override
    public List<Account> findAccountsByIdAndName(UUID id, String name) {
        log.info("find all accounts where name - " + name);
        return validatorService.checkList(accountRepository.findAccountsByIdAndName(id, name));
    }

    @Override
    public List<Account> findAccountsByIdAndStatus(UUID id, AccountStatus status) {
        log.info("find all accounts where status - " + status);
        return validatorService.checkList(accountRepository.findAccountsByIdAndStatus(id, status));
    }

    @Override
    public List<Account> findAccountsByIdAndType(UUID id, AccountType type) {
        log.info("find all accounts where type - " + type);
        return validatorService.checkList(accountRepository.findAccountsByIdAndType(id, type));
    }

    @Override
    public List<Account> findAccountsByIdAndCurrencyCode(UUID id, CurrencyCode currencyCode) {
        log.info("find all accounts where currency code - " + currencyCode);
        return validatorService.checkList(accountRepository.findAccountsByIdAndCurrencyCode(id, currencyCode));
    }

    @Override
    public List<Account> findAccountsByCreatedAt(Timestamp dateCreation) {
        log.info("find all accounts where date of creation - " + dateCreation);
        return validatorService.checkList(accountRepository.findAccountsByCreatedAt(dateCreation));
    }

    @Override
    public List<Account> findAccountsByUpdatedAt(Timestamp dateUpdate) {
        log.info("find all accounts where date of update - " + dateUpdate);
        return validatorService.checkList(accountRepository.findAccountsByUpdatedAt(dateUpdate));
    }

    @Override
    public Account findAccountByIban(String iban) {
        return getEntity.getEntity(accountRepository.findByIBan(iban));
    }

    @Override
    @Transactional
    public Boolean updateAccountById(UUID id, Account accountFromFE) {
        Account accountFromDB = getEntity.getEntity(accountRepository.findAccountById(id));
        Account accountToUpdate = accountConverter.convertFields(accountFromDB, accountFromFE);
        accountRepository.save(accountToUpdate);
        log.info("Account updated successfully for client ID: " + id);
        return true;
    }


    @Override
    @Transactional
    public void updateStatusById(UUID id, AccountStatus status){
        Account accountFromDB = getEntity.getEntity(accountRepository.findAccountById(id));
        accountFromDB.setStatus(status);
        accountRepository.save(accountFromDB);
        log.info("Account status successfully updated: " + id);
    }

    @Override
    @Transactional
    public Account deleteAccountById(UUID id){
        Account deletedAccount = getEntity.getEntity(accountRepository.findAccountById(id));
        deletedAccount.setDeletedStatus(DeletedStatus.DELETED);
        accountRepository.save(deletedAccount);
        log.info("delete account where UUID - " + id);
        return deletedAccount;
    }

    @Override
    @Transactional
    public List<Account> deleteAccountsByIdAndStatus(UUID id, AccountStatus status) {
        List<Account> accounts = validatorService.checkList(accountRepository.findAccountsByIdAndStatus(id, status));
        accounts.forEach(account -> account.setDeletedStatus(DeletedStatus.DELETED));
        log.info("deleting accounts where Account Status - " + status);
        return accounts;
    }

    @Override
    @Transactional
    public Account restoreById(UUID id) {
        Account accountToRestore = getEntity.getEntity(accountRepository.findAccountById(id));
        accountToRestore.setDeletedStatus(DeletedStatus.ACTIVE);
        accountRepository.save(accountToRestore);
        log.info("account restored");
        return accountToRestore;
    }

    @Override
    public Account findAccountByIdAndCurrencyCode(UUID id, CurrencyCode currencyCode) {
        return validatorService.checkEntity(accountRepository.findAccountByIdAndCurrencyCode(id, currencyCode));
    }

    @Override
    @Transactional
    public List<Account> restoreAllById(UUID id) {
        List<Account> accounts = validatorService.checkList(
                accountRepository.findAccountsByIdAndDeletedStatus(id, DeletedStatus.DELETED));
        accounts.forEach(account -> account.setDeletedStatus(DeletedStatus.ACTIVE));
        accountRepository.saveAll(accounts);
        return accounts;
    }
}
