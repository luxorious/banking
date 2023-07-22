package com.banking.service.interfaces;

import com.banking.entity.Account;
import com.banking.entity.entityenumerations.AccountStatus;
import com.banking.entity.entityenumerations.AccountType;
import com.banking.entity.entityenumerations.CurrencyCode;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

/**
 * The AccountService interface defines the contract for managing bank accounts in the banking system.
 * It provides methods for creating, retrieving, updating, and deleting bank accounts.
 * The interface also allows for searching and restoring accounts based on various criteria.
 */
public interface AccountService {
    /**
     * Saves a new or existing bank account to the database.
     *
     * @param account The account to be saved.
     * @return The saved account.
     */
    Account save(Account account);

    /**
     * Creates a new bank account for the specified client.
     *
     * @param account  The account to be created.
     * @param clientId The ID of the client who owns the account.
     * @return The created account.
     */
    Account createAccount(Account account, UUID clientId);

    /**
     * Retrieves all active bank accounts associated with the specified client ID.
     *
     * @param id The ID of the client.
     * @return A list of active accounts owned by the client.
     */
    List<Account> findAllActiveById(UUID id);

    /**
     * Retrieves a bank account by its unique ID.
     *
     * @param id The ID of the account to be retrieved.
     * @return The account with the specified ID, or null if not found.
     */
    Account findAccountById(UUID id);

    /**
     * Retrieves bank accounts based on the provided ID and account name.
     *
     * @param id   The ID of the client.
     * @param name The account name to search for.
     * @return A list of accounts with the specified ID and name.
     */
    List<Account> findAccountsByIdAndName(UUID id, String name);

    /**
     * Retrieves bank accounts based on the provided ID and account status.
     *
     * @param id     The ID of the client.
     * @param status The account status to search for.
     * @return A list of accounts with the specified ID and status.
     */
    List<Account> findAccountsByIdAndStatus(UUID id, AccountStatus status);

    /**
     * Retrieves bank accounts based on the provided ID and account type.
     *
     * @param id   The ID of the client.
     * @param type The account type to search for.
     * @return A list of accounts with the specified ID and type.
     */
    List<Account> findAccountsByIdAndType(UUID id, AccountType type);

    /**
     * Retrieves bank accounts based on the provided ID and currency code.
     *
     * @param id           The ID of the client.
     * @param currencyCode The currency code to search for.
     * @return A list of accounts with the specified ID and currency code.
     */
    List<Account> findAccountsByIdAndCurrencyCode(UUID id, CurrencyCode currencyCode);

    /**
     * Retrieves bank accounts created at a specific date and time.
     *
     * @param dateCreation The timestamp representing the date and time of account creation.
     * @return A list of accounts created at the specified date and time.
     */
    List<Account> findAccountsByCreatedAt(Timestamp dateCreation);

    /**
     * Retrieves bank accounts updated at a specific date and time.
     *
     * @param dateUpdate The timestamp representing the date and time of account update.
     * @return A list of accounts updated at the specified date and time.
     */
    List<Account> findAccountsByUpdatedAt(Timestamp dateUpdate);

    /**
     * Updates an existing bank account with new data provided in the accountFromDB parameter.
     *
     * @param id            The ID of the account to be updated.
     * @param accountFromDB The updated account data.
     * @return True if the account is successfully updated, otherwise false.
     */
    Boolean updateAccountById(UUID id, Account accountFromDB);

    /**
     * Updates the status of a bank account with the specified ID.
     *
     * @param id     The ID of the account to be updated.
     * @param status The new account status to be set.
     */
    void updateStatusById(UUID id, AccountStatus status);

    /**
     * Deletes a bank account with the specified ID.
     *
     * @param id The ID of the account to be deleted.
     * @return The deleted account, or a new Account object if the account is not found.
     */
    Account deleteAccountById(UUID id);

    /**
     * Deletes all bank accounts with the specified status for the specified client.
     *
     * @param id     The ID of the client.
     * @param status The status of the accounts to be deleted.
     * @return A list of deleted accounts.
     */
    List<Account> deleteAccountsByIdAndStatus(UUID id, AccountStatus status);

    /**
     * Restores a deleted bank account with the specified ID.
     *
     * @param id The ID of the account to be restored.
     * @return The restored account, or a new Account object if the account is not found.
     */
    Account restoreById(UUID id);

    /**
     * Retrieves a bank account by its unique ID and currency code.
     *
     * @param id           The ID of the account to be retrieved.
     * @param currencyCode The currency code of the account.
     * @return The account with the specified ID and currency code, or null if not found.
     */
    Account findAccountByIdAndCurrencyCode(UUID id, CurrencyCode currencyCode);

    /**
     * Restores all deleted bank accounts associated with the specified client ID.
     *
     * @param id The ID of the client.
     * @return A list of restored accounts owned by the client.
     */
    List<Account> restoreAllById(UUID id);

    /**
     * Retrieves all deleted bank accounts.
     *
     * @return A list of all deleted accounts.
     */
    List<Account> findAllDeleted();

    /**
     * Retrieves all bank accounts for administrative purposes.
     *
     * @return A list of all bank accounts available for administrators.
     */
    List<Account> findAllAccountsForAdmin();

}
