package com.banking.controller;

import com.banking.entity.Account;
import com.banking.entity.entityenumerations.AccountStatus;
import com.banking.entity.entityenumerations.AccountType;
import com.banking.entity.entityenumerations.CurrencyCode;
import com.banking.service.interfaces.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

/**
 * Controller for managing bank accounts.
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    /**
     * Get an account by its ID.
     *
     * @param id The ID of the account to retrieve.
     * @return The account matching the provided ID.
     */
    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Account getAccountById(@PathVariable UUID id) {
        return accountService.findAccountById(id);
    }

    /**
     * Create a new account for the specified client ID.
     *
     * @param account  The account details to create.
     * @param clientId The ID of the client for whom the account is created.
     * @return The newly created account.
     */
    @PostMapping(value = "/create/{clientId}")
    @ResponseStatus(HttpStatus.OK)
    public Account createAccount(@RequestBody Account account, @PathVariable UUID clientId) {
        log.info("account " + account + " added");
        return accountService.createAccount(account, clientId);
    }


    /**
     * Get a list of accounts by their name and associated client ID.
     *
     * @param id   The ID of the client associated with the accounts.
     * @param name The name to search for in the account records.
     * @return A list of accounts matching the given name and client ID.
     */
    @GetMapping(value = "/name/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> findAccountsByName(@PathVariable UUID id, @RequestParam String name) {
        return accountService.findAccountsByIdAndName(id, name);
    }

    /**
     * Get a list of accounts by their status and associated client ID.
     *
     * @param id     The ID of the client associated with the accounts.
     * @param status The status to filter the accounts.
     * @return A list of accounts matching the given status and client ID.
     */
    @GetMapping(value = "/status/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> findAccountsByStatus(@PathVariable UUID id, @RequestParam AccountStatus status) {
        return accountService.findAccountsByIdAndStatus(id, status);
    }

    /**
     * Get a list of all active accounts associated with the given client ID.
     *
     * @param id The ID of the client associated with the accounts.
     * @return A list of all active accounts for the provided client ID.
     */
    @GetMapping(value = "/get-all/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> getAll(@PathVariable UUID id){
        return accountService.findAllActiveById(id);
    }

    /**
     * Get a list of accounts by their type and associated client ID.
     *
     * @param id   The ID of the client associated with the accounts.
     * @param type The account type to filter the accounts.
     * @return A list of accounts matching the given type and client ID.
     */
    @GetMapping(value = "/type/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> findAccountsByType(@PathVariable UUID id, @RequestParam AccountType type) {
        return accountService.findAccountsByIdAndType(id, type);
    }

    /**
     * Get a list of accounts by their currency code and associated client ID.
     *
     * @param id            The ID of the client associated with the accounts.
     * @param currencyCode  The currency code to filter the accounts.
     * @return A list of accounts matching the given currency code and client ID.
     */
    @GetMapping(value = "/currency-code/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> findAccountsByCurrencyCode(@PathVariable UUID id, @RequestParam CurrencyCode currencyCode) {
        return accountService.findAccountsByIdAndCurrencyCode(id, currencyCode);
    }

    /**
     * Get a list of accounts by their creation date.
     *
     * @param dateCreation The creation date to filter the accounts.
     * @return A list of accounts created on the given date.
     */
    @GetMapping(value = "/date-create/{dateCreation}")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> findAccountsByCreatedAt(@PathVariable Timestamp dateCreation) {
        return accountService.findAccountsByCreatedAt(dateCreation);
    }

    /**
     * Get a list of accounts by their last update date.
     *
     * @param dateUpdate The last update date to filter the accounts.
     * @return A list of accounts updated on the given date.
     */
    @GetMapping(value = "/date-update/{dateUpdate}")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> findAccountsByUpdatedAt(@PathVariable Timestamp dateUpdate) {
        return accountService.findAccountsByUpdatedAt(dateUpdate);
    }

    /**
     * Update an existing account by its ID with the provided account details from the front-end.
     *
     * @param id            The ID of the account to update.
     * @param accountFromFE The updated account details received from the front-end.
     * @return ResponseEntity with the updated account if the update was successful, or not found status otherwise.
     */
   @PutMapping(value = "/update/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable UUID id, @RequestBody Account accountFromFE){
        boolean update = accountService.updateAccountById(id, accountFromFE);
        if (update){
            return ResponseEntity.ok(accountFromFE);
        }
        return ResponseEntity.notFound().build();
    }


    /**
     * Update the status of an existing account by its ID.
     *
     * @param id     The ID of the account to update.
     * @param status The new status to set for the account.
     */
    @PutMapping(value = "/update/status/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateStatusById(@PathVariable UUID id, @RequestParam AccountStatus status){
        accountService.updateStatusById(id, status);
    }

    /**
     * Delete an account by its ID.
     *
     * @param id The ID of the account to delete.
     * @return The deleted account, or null if no account was found with the given ID.
     */
    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Account deleteAccountById(@PathVariable UUID id){
        return accountService.deleteAccountById(id);
    }

    /**
     * Delete accounts by their status and associated client ID.
     *
     * @param id     The ID of the client associated with the accounts.
     * @param status The status of the accounts to delete.
     * @return A list of deleted accounts that matched the given status and client ID.
     */
    @DeleteMapping(value = "/delete/status/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> deleteAccountsByStatus(@PathVariable UUID id, @RequestParam AccountStatus status){
        return accountService.deleteAccountsByIdAndStatus(id, status);
    }

    /**
     * Restore a previously deleted account by its ID.
     *
     * @param id The ID of the account to restore.
     * @return The restored account, or null if no account was found with the given ID.
     */
    @PutMapping(value = "/restore/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Account restoreById(@PathVariable UUID id){
        return accountService.restoreById(id);
    }

    /**
     * Restore all previously deleted accounts associated with the given client ID.
     *
     * @param id The ID of the client associated with the accounts.
     * @return A list of restored accounts, or an empty list if no deleted accounts were found for the given client ID.
     */
    @PutMapping(value = "/restore/all/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> restoreAll(@PathVariable UUID id){
        return accountService.restoreAllById(id);
    }
}
