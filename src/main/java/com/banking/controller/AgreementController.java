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

/**
 * REST controller for managing agreements.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/agreement")
public class AgreementController {

    private final AgreementService agreementService;

    /**
     * Create a new agreement associated with the provided product ID.
     *
     * @param agreement The agreement entity to create.
     * @param productId The ID of the product associated with the agreement.
     * @return The created agreement entity.
     */
    @PutMapping(value = "/create/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public Agreement createAgreement(@RequestBody Agreement agreement, @PathVariable UUID productId) {
        return agreementService.createAgreement(agreement, productId);
    }

    /**
     * Get a list of all agreements.
     *
     * @return A list of all agreements.
     */
    @GetMapping(value = "/find-all")
    @ResponseStatus(HttpStatus.OK)
    public List<Agreement> findAll() {
        return agreementService.findAll();
    }

    /**
     * Find agreements by the provided interest rate.
     *
     * @param interestRate The interest rate to filter the agreements.
     * @return A list of agreements that match the provided interest rate.
     */
    @GetMapping(value = "/find/interest-rate/{interestRate}")
    @ResponseStatus(HttpStatus.OK)
    public List<Agreement> findAgreementByInterestRate(@PathVariable BigDecimal interestRate) {
        return agreementService.findAgreementByInterestRate(interestRate);
    }

    /**
     * Edit an existing agreement by its ID using the updated agreement details.
     *
     * @param id          The ID of the agreement to edit.
     * @param agreementFE The updated agreement details received from the front-end.
     * @return The edited agreement entity, or null if no agreement was found with the given ID.
     */
    @PostMapping(value = "/edit/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Agreement editAgreement(@PathVariable UUID id, @RequestBody Agreement agreementFE) {
        return agreementService.editAgreement(id, agreementFE);
    }

    /**
     * Delete a specific agreement by its ID.
     *
     * @param id The ID of the agreement to delete.
     * @return The deleted agreement entity, or null if no agreement was found with the given ID.
     */
    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Agreement deleteAgreementById(@PathVariable UUID id) {
        return agreementService.deleteAgreementById(id);
    }

    /**
     * Delete all agreements with the provided status.
     *
     * @param status The status to filter the agreements.
     * @return A list of deleted agreements that match the provided status.
     */
    @DeleteMapping(value = "/delete/all-by-status/{status}")
    @ResponseStatus(HttpStatus.OK)
    public List<Agreement> deleteAgreementsByStatus(@PathVariable AgreementStatus status) {
        return agreementService.deleteAgreementsByStatus(status);
    }

    /**
     * Change the sum of an existing agreement by its ID.
     *
     * @param id      The ID of the agreement to edit.
     * @param newSum  The new sum to be set for the agreement.
     * @return The updated agreement entity, or null if no agreement was found with the given ID.
     */
    @PutMapping(value = "/edit/sum/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Agreement changeSumById(@PathVariable UUID id, @RequestParam BigDecimal newSum) {
        return agreementService.changeSumById(id, newSum);
    }
}

