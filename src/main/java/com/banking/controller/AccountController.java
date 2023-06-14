package com.banking.controller;

import com.banking.entity.Account;
import com.banking.entity.entityEnumerations.AccountStatus;
import com.banking.entity.entityEnumerations.AccountType;
import com.banking.entity.entityEnumerations.CurrencyCode;
import com.banking.service.interfaces.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AccountController {

    private final AccountService accountService;

    @GetMapping(value = "/account/{id}")
    public Optional<Account> getAccountById(@PathVariable UUID id) {
        return accountService.findAccountById(id);
    }

    @PostMapping(value = "/create_account")
    public void createAccount(@RequestBody Account account) {
        accountService.createAccount(account);
        log.info("account " + account + " added");
    }

    @GetMapping(value = "/accounts/{name}")
    public List<Account> findAccountsByName(@PathVariable String name) {
        return accountService.findAccountsByName(name);
    }

    @GetMapping(value = "/accounts/{status}")
    public List<Account> findAccountsByStatus(@PathVariable AccountStatus status) {
        return accountService.findAccountsByStatus(status);
    }

    @GetMapping(value = "/account/{type}")
    public List<Account> findAccountsByType(@PathVariable AccountType type) {
        return accountService.findAccountsByType(type);
    }

    @GetMapping(value = "/account/{currencyCode}")
    public List<Account> findAccountsByCurrencyCode(@PathVariable CurrencyCode currencyCode) {
        return accountService.findAccountsByCurrencyCode(currencyCode);
    }

    @GetMapping(value = "/account/{dateCreation}")
    public List<Account> findAccountsByCreatedAt(@PathVariable Timestamp dateCreation) {
        return accountService.findAccountsByCreatedAt(dateCreation);
    }

    @GetMapping(value = "/account/{dateUpdate}")
    public List<Account> findAccountsByUpdatedAt(@PathVariable Timestamp dateUpdate) {
        return accountService.findAccountsByUpdatedAt(dateUpdate);
    }
}
