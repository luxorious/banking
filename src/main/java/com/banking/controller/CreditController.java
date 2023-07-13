package com.banking.controller;

import com.banking.entity.Credit;
import com.banking.entity.pojo.CreditData;
import com.banking.service.interfaces.CreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/credit")
public class CreditController {

    private final CreditService creditService;

    @PostMapping("/create/{clientId}")
    public Credit createCredit(@RequestBody CreditData creditData, @PathVariable UUID clientId) {
        return creditService.createCredit(creditData, clientId);
    }

    @GetMapping("/get-all/client/{clientId}")
    public List<Credit> findAllCreditsByClientId(@PathVariable UUID clientId) {
        return creditService.findAllCreditsByClientId(clientId);
    }

    @GetMapping("/get-all/active/{clientId}")
    public List<Credit> findAllActiveByClientId(@PathVariable UUID clientId){
        return creditService.findAllActiveByClientId(clientId);
    }

    @GetMapping("/get-all")
    public List<Credit> findAll() {
        return creditService.findAll();
    }

    @GetMapping("/get-all/active")
    public List<Credit> findAllActive() {
        return creditService.findAllActive();
    }
}