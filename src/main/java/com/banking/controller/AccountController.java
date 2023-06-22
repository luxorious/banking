package com.banking.controller;

import com.banking.entity.Account;
import com.banking.entity.entityEnumerations.AccountStatus;
import com.banking.entity.entityEnumerations.AccountType;
import com.banking.entity.entityEnumerations.CurrencyCode;
import com.banking.service.interfaces.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
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

    @PostMapping(value = "/create_account")
    @ResponseStatus(HttpStatus.OK)
    public Account createAccount(@RequestBody Account account) {
        log.info("account " + account + " added");
        return accountService.createAccount(account);
    }

    @GetMapping(value = "/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> findAccountsByName(@PathVariable String name) {
        return accountService.findAccountsByName(name);
    }

    @GetMapping(value = "/status/{status}")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> findAccountsByStatus(@PathVariable AccountStatus status) {
        return accountService.findAccountsByStatus(status);
    }

    @GetMapping(value = "/get-all")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> getAll(){
        return accountService.findAllActive();
    }

    @GetMapping(value = "/type/{type}")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> findAccountsByType(@PathVariable AccountType type) {
        return accountService.findAccountsByType(type);
    }

    @GetMapping(value = "/currency-code/{currencyCode}")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> findAccountsByCurrencyCode(@PathVariable CurrencyCode currencyCode) {
        return accountService.findAccountsByCurrencyCode(currencyCode);
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

    @DeleteMapping(value = "/delete/status/{status}")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> deleteAccountsByStatus(@PathVariable AccountStatus status){
        return accountService.deleteAccountsByStatus(status);
    }

    @PutMapping(value = "/restore/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Account restoreById(@PathVariable UUID id){
        return accountService.restoreById(id);
    }

    @PutMapping(value = "/restore/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> restoreAll(){
        return accountService.restoreAll();
    }
}
