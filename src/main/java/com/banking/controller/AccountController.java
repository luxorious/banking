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

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Account getAccountById(@PathVariable UUID id) {
        return accountService.findAccountById(id);
    }

    @PostMapping(value = "/create/{clientId}")
    @ResponseStatus(HttpStatus.OK)
    public Account createAccount(@RequestBody Account account, @PathVariable UUID clientId) {
        log.info("account " + account + " added");
        return accountService.createAccount(account, clientId);
    }

    @GetMapping(value = "/name/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> findAccountsByName(@PathVariable UUID id, @RequestParam String name) {
        return accountService.findAccountsByIdAndName(id, name);
    }

    @GetMapping(value = "/status/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> findAccountsByStatus(@PathVariable UUID id, @RequestParam AccountStatus status) {
        return accountService.findAccountsByIdAndStatus(id, status);
    }

    @GetMapping(value = "/get-all/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> getAll(@PathVariable UUID id){
        return accountService.findAllActiveById(id);
    }

    @GetMapping(value = "/type/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> findAccountsByType(@PathVariable UUID id, @RequestParam AccountType type) {
        return accountService.findAccountsByIdAndType(id, type);
    }

    @GetMapping(value = "/currency-code/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> findAccountsByCurrencyCode(@PathVariable UUID id, @RequestParam CurrencyCode currencyCode) {
        return accountService.findAccountsByIdAndCurrencyCode(id, currencyCode);
    }

    @GetMapping(value = "/date-create/{dateCreation}")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> findAccountsByCreatedAt(@PathVariable Timestamp dateCreation) {
        return accountService.findAccountsByCreatedAt(dateCreation);
    }

    @GetMapping(value = "/date-update/{dateUpdate}")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> findAccountsByUpdatedAt(@PathVariable Timestamp dateUpdate) {
        return accountService.findAccountsByUpdatedAt(dateUpdate);
    }

   @PutMapping(value = "/update/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable UUID id, @RequestBody Account accountFromFE){
        boolean update = accountService.updateAccountById(id, accountFromFE);
        if (update){
            return ResponseEntity.ok(accountFromFE);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping(value = "/update/status/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateStatusById(@PathVariable UUID id, @RequestParam AccountStatus status){
        accountService.updateStatusById(id, status);
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Account deleteAccountById(@PathVariable UUID id){
        return accountService.deleteAccountById(id);
    }

    @DeleteMapping(value = "/delete/status/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> deleteAccountsByStatus(@PathVariable UUID id, @RequestParam AccountStatus status){
        return accountService.deleteAccountsByIdAndStatus(id, status);
    }

    @PutMapping(value = "/restore/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Account restoreById(@PathVariable UUID id){
        return accountService.restoreById(id);
    }

    @PutMapping(value = "/restore/all/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> restoreAll(@PathVariable UUID id){
        return accountService.restoreAllById(id);
    }
}
