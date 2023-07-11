package com.banking.controller;

import com.banking.entity.Agreement;
import com.banking.entity.entityenumerations.AgreementStatus;
import com.banking.service.interfaces.AgreementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/agreement")
public class AgreementController {

    private final AgreementService agreementService;

    @PutMapping(value = "/create/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public Agreement createAgreement(@RequestBody Agreement agreement, @PathVariable UUID productId) {
        return agreementService.createAgreement(agreement, productId);
    }

    @GetMapping(value = "/find-all")
    @ResponseStatus(HttpStatus.OK)
    public List<Agreement> findAll() {
        return agreementService.findAll();
    }

    @GetMapping(value = "/find/interest-rate/{interestRate}")
    @ResponseStatus(HttpStatus.OK)
    public List<Agreement> findAgreementByInterestRate(@PathVariable BigDecimal interestRate) {
        return agreementService.findAgreementByInterestRate(interestRate);
    }

    @PostMapping(value = "/edit/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Agreement editAgreement(@PathVariable UUID id, @RequestBody Agreement agreementFE) {
        return agreementService.editAgreement(id, agreementFE);
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Agreement deleteAgreementById(@PathVariable UUID id) {
        return agreementService.deleteAgreementById(id);
    }

    @DeleteMapping(value = "/delete/all-by-status/{status}")
    @ResponseStatus(HttpStatus.OK)
    public List<Agreement> deleteAgreementsByStatus(@PathVariable AgreementStatus status) {
        return agreementService.deleteAgreementsByStatus(status);
    }

    @PutMapping(value = "/edit/sum/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Agreement changeSumById(@PathVariable UUID id, @RequestParam BigDecimal newSum) {
        return agreementService.changeSumById(id, newSum);
    }
}

