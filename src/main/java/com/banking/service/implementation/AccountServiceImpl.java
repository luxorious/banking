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

/**
 * This class provides implementation for the {@link AccountService} interface.
 * It is responsible for managing accounts, including creating, updating, deleting, and restoring accounts.
 *
 * @author [Your Name]
 * @version [Version Number]
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final Converter<Account> accountConverter;
    private final IBanGeneratorComponent iBanGenerator;
    private final GetEntity<Account> getEntity;
    private final ValidatorService<Account> validatorService;

    /**
     * Saves the given account in the database.
     *
     * @param account The account to be saved.
     * @return The saved account.
     */
    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    /**
     * Creates a new account with the provided client ID and saves it in the database.
     * The account is set to ACTIVE status and assigned a generated IBAN.
     *
     * @param account  The account to be created.
     * @param clientId The UUID of the client to whom the account belongs.
     * @return The newly created and saved account.
     */
    @Override
    public Account createAccount(Account account, UUID clientId) {
        account.setDeletedStatus(DeletedStatus.ACTIVE);
        account.setIBan(iBanGenerator.generate());
        account.setClientId(clientId);
        log.info("creating account " + account);
        return accountRepository.save(account);
    }

    /**
     * Retrieves a list of all active accounts associated with the specified client ID.
     *
     * @param id The UUID of the client.
     * @return A list of active accounts associated with the client ID.
     */
    @Override
    public List<Account> findAllActiveById(UUID id) {
        log.info("get all active accounts");
        return validatorService.checkList(accountRepository.findAccountsByIdAndDeletedStatus(id, DeletedStatus.ACTIVE));
    }

    /**
     * Retrieves a list of all deleted accounts.
     *
     * @return A list of deleted accounts.
     */
    @Override
    public List<Account> findAllDeleted() {
        return validatorService.checkList(accountRepository.findAccountsByDeletedStatus(DeletedStatus.DELETED));
    }

    /**
     * Retrieves a list of all accounts for admin use.
     *
     * @return A list of all accounts.
     */
    @Override
    public List<Account> findAllAccountsForAdmin() {
        return validatorService.checkList(accountRepository.findAll());
    }

    /**
     * Retrieves the account with the specified UUID if it exists.
     *
     * @param uuid The UUID of the account to find.
     * @return The account with the given UUID if it exists.
     */
    @Override
    public Account findAccountById(UUID uuid) {
        log.info("find account if exist with UUID - " + uuid);
        return getEntity.getEntity(accountRepository.findAccountById(uuid));
    }

    /**
     * Retrieves a list of accounts associated with the specified client ID and name.
     *
     * @param id   The UUID of the client.
     * @param name The name of the accounts to find.
     * @return A list of accounts associated with the client ID and name.
     */
    @Override
    public List<Account> findAccountsByIdAndName(UUID id, String name) {
        log.info("find all accounts where name - " + name);
        return validatorService.checkList(accountRepository.findAccountsByIdAndName(id, name));
    }

    /**
     * Retrieves a list of accounts associated with the specified client ID and status.
     *
     * @param id     The UUID of the client.
     * @param status The status of the accounts to find.
     * @return A list of accounts associated with the client ID and status.
     */
    @Override
    public List<Account> findAccountsByIdAndStatus(UUID id, AccountStatus status) {
        log.info("find all accounts where status - " + status);
        return validatorService.checkList(accountRepository.findAccountsByIdAndStatus(id, status));
    }

    /**
     * Retrieves a list of accounts associated with the specified client ID and type.
     *
     * @param id   The UUID of the client.
     * @param type The type of the accounts to find.
     * @return A list of accounts associated with the client ID and type.
     */
    @Override
    public List<Account> findAccountsByIdAndType(UUID id, AccountType type) {
        log.info("find all accounts where type - " + type);
        return validatorService.checkList(accountRepository.findAccountsByIdAndType(id, type));
    }

    /**
     * Retrieves a list of accounts associated with the specified client ID and currency code.
     *
     * @param id           The UUID of the client.
     * @param currencyCode The currency code of the accounts to find.
     * @return A list of accounts associated with the client ID and currency code.
     */
    @Override
    public List<Account> findAccountsByIdAndCurrencyCode(UUID id, CurrencyCode currencyCode) {
        log.info("find all accounts where currency code - " + currencyCode);
        return validatorService.checkList(accountRepository.findAccountsByIdAndCurrencyCode(id, currencyCode));
    }

    /**
     * Retrieves a list of accounts created at the specified date of creation.
     *
     * @param dateCreation The date of creation to filter the accounts.
     * @return A list of accounts created at the specified date of creation.
     */
    @Override
    public List<Account> findAccountsByCreatedAt(Timestamp dateCreation) {
        log.info("find all accounts where date of creation - " + dateCreation);
        return validatorService.checkList(accountRepository.findAccountsByCreatedAt(dateCreation));
    }

    /**
     * Retrieves a list of accounts updated at the specified date of update.
     *
     * @param dateUpdate The date of update to filter the accounts.
     * @return A list of accounts updated at the specified date of update.
     */
    @Override
    public List<Account> findAccountsByUpdatedAt(Timestamp dateUpdate) {
        log.info("find all accounts where date of update - " + dateUpdate);
        return validatorService.checkList(accountRepository.findAccountsByUpdatedAt(dateUpdate));
    }

    /**
     * Updates the account with the specified ID using the information from the provided Account object.
     *
     * @param id            The UUID of the account to update.
     * @param accountFromFE The Account object containing the updated information.
     * @return true if the account was updated successfully.
     */
    @Override
    @Transactional
    public Boolean updateAccountById(UUID id, Account accountFromFE) {
        Account accountFromDB = getEntity.getEntity(accountRepository.findAccountById(id));
        Account accountToUpdate = accountConverter.convertFields(accountFromDB, accountFromFE);
        accountRepository.save(accountToUpdate);
        log.info("Account updated successfully for client ID: " + id);
        return true;
    }

    /**
     * Updates the status of the account with the specified ID.
     *
     * @param id     The UUID of the account to update.
     * @param status The new status of the account.
     */
    @Override
    @Transactional
    public void updateStatusById(UUID id, AccountStatus status) {
        Account accountFromDB = getEntity.getEntity(accountRepository.findAccountById(id));
        accountFromDB.setStatus(status);
        accountRepository.save(accountFromDB);
        log.info("Account status successfully updated: " + id);
    }

    /**
     * Soft deletes the account with the specified ID by setting its deleted status to "DELETED".
     *
     * @param id The UUID of the account to delete.
     * @return The deleted Account object.
     */
    @Override
    @Transactional
    public Account deleteAccountById(UUID id) {
        Account deletedAccount = getEntity.getEntity(accountRepository.findAccountById(id));
        deletedAccount.setDeletedStatus(DeletedStatus.DELETED);
        accountRepository.save(deletedAccount);
        log.info("delete account where UUID - " + id);
        return deletedAccount;
    }

    /**
     * Soft deletes multiple accounts associated with the specified client ID and status
     * by setting their deleted status to "DELETED".
     *
     * @param id     The UUID of the client.
     * @param status The status of the accounts to delete.
     * @return A list of deleted Account objects.
     */
    @Override
    @Transactional
    public List<Account> deleteAccountsByIdAndStatus(UUID id, AccountStatus status) {
        List<Account> accounts = validatorService.checkList(accountRepository.findAccountsByIdAndStatus(id, status));
        accounts.forEach(account -> account.setDeletedStatus(DeletedStatus.DELETED));
        log.info("deleting accounts where Account Status - " + status);
        return accounts;
    }

    /**
     * Restores the soft-deleted account with the specified ID by setting its deleted status to ACTIVE.
     *
     * @param id The UUID of the account to restore.
     * @return The restored Account object.
     */
    @Override
    @Transactional
    public Account restoreById(UUID id) {
        Account accountToRestore = getEntity.getEntity(accountRepository.findAccountById(id));
        accountToRestore.setDeletedStatus(DeletedStatus.ACTIVE);
        accountRepository.save(accountToRestore);
        log.info("account restored");
        return accountToRestore;
    }

    /**
     * Finds the account with the specified ID and currency code.
     *
     * @param id           The UUID of the account to find.
     * @param currencyCode The currency code of the account.
     * @return The Account object with the specified ID and currency code.
     */
    @Override
    public Account findAccountByIdAndCurrencyCode(UUID id, CurrencyCode currencyCode) {
        return validatorService.checkEntity(accountRepository.findAccountByIdAndCurrencyCode(id, currencyCode));
    }

    /**
     * Restores all soft-deleted accounts associated with the specified client ID by setting their deleted status to ACTIVE.
     *
     * @param id The UUID of the client.
     * @return A list of restored Account objects.
     */
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
