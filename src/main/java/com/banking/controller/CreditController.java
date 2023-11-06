package com.banking.controller;

import com.banking.entity.Credit;
import com.banking.entity.pojo.CreditData;
import com.banking.service.interfaces.CreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * REST controller for managing credits.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/credit")
public class CreditController {

    private final CreditService creditService;

    /**
     * Create a new credit associated with the provided client ID.
     *
     * @param creditData The credit data to create the credit.
     * @param clientId   The ID of the client associated with the credit.
     * @return The created credit entity.
     */
    @PostMapping("/create/{clientId}")
    public Credit createCredit(@RequestBody CreditData creditData, @PathVariable UUID clientId) {
        return creditService.createCredit(creditData, clientId);
    }

    /**
     * Find all credits associated with the provided client ID.
     *
     * @param clientId The ID of the client to filter the credits.
     * @return A list of credits that belong to the specified client.
     */
    @GetMapping("/get-all/client/{clientId}")
    public List<Credit> findAllCreditsByClientId(@PathVariable UUID clientId) {
        return creditService.findAllCreditsByClientId(clientId);
    }

    /**
     * Find all active credits associated with the provided client ID.
     *
     * @param clientId The ID of the client to filter the active credits.
     * @return A list of active credits that belong to the specified client.
     */
    @GetMapping("/get-all/active/{clientId}")
    public List<Credit> findAllActiveByClientId(@PathVariable UUID clientId){
        return creditService.findAllActiveByClientId(clientId);
    }

    /**
     * Find all credits (active, paid and transferred to collectors).
     *
     * @return A list of all existing credits.
     */
    @GetMapping("/get-all")
    public List<Credit> findAll() {
        return creditService.findAll();
    }

    /**
     * Find all active credits.
     *
     * @return A list of all active credits.
     */
    @GetMapping("/get-all/active")
    public List<Credit> findAllActive() {
        return creditService.findAllActive();
    }
}