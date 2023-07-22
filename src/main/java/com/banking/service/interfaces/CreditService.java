package com.banking.service.interfaces;

import com.banking.entity.Credit;
import com.banking.entity.pojo.CreditData;

import java.util.List;
import java.util.UUID;

/**
 * The CreditService interface defines the contract for managing credits in the banking system.
 * It provides methods for creating, retrieving, and managing credit information.
 */
public interface CreditService {
    /**
     * Checks if a credit can be approved based on the provided credit data.
     *
     * @param creditData The credit data to be checked for approval.
     * @return True if the credit can be approved, false otherwise.
     */
    Boolean getCredit(CreditData creditData);

    /**
     * Creates a new credit with the provided credit data and associates it with the given client ID.
     *
     * @param creditData The credit data to create the credit.
     * @param clientId   The ID of the client associated with the credit.
     * @return The created credit.
     */
    Credit createCredit(CreditData creditData, UUID clientId);

    /**
     * Retrieves a list of all credits associated with the specified client ID.
     *
     * @param clientId The ID of the client whose credits are to be retrieved.
     * @return A list of credits associated with the specified client ID.
     */
    List<Credit> findAllCreditsByClientId(UUID clientId);

    /**
     * Retrieves a list of all active credits associated with the specified client ID.
     *
     * @param clientId The ID of the client whose active credits are to be retrieved.
     * @return A list of active credits associated with the specified client ID.
     */
    List<Credit> findAllActiveByClientId(UUID clientId);

    /**
     * Retrieves a list of all credits in the system.
     *
     * @return A list of all credits.
     */
    List<Credit> findAll();

    /**
     * Retrieves a list of all active credits in the system.
     *
     * @return A list of all active credits.
     */
    List<Credit> findAllActive();

    /**
     * Saves a new or existing credit to the database.
     *
     * @param credit The credit to be saved.
     * @return The saved credit.
     */
    Credit save(Credit credit);
}
