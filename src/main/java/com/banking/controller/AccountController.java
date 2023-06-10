package com.banking.controller;

import com.banking.entity.Account;
import com.banking.service.interfaces.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping(value = "/{id}")
    public Optional<Account> getAccountById(@PathVariable UUID id) {
        return accountService.findAccountById(id);
    }

    @PostMapping(value = "/create_account")
    public String createAccount(@RequestBody Account account) {
        accountService.createAccount(account);

        if (account.getId() == null) {
            return "nothing to create";
        } else {
//            accountService.createAccount(account);
            return "Account created";
        }
    }
}
