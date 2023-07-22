package com.banking.service.interfaces;

import com.banking.entity.Agreement;
import com.banking.entity.entityenumerations.AgreementStatus;


import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * The AgreementService interface defines the contract for managing agreements in the banking system.
 * It provides methods for creating, retrieving, updating, and deleting agreements.
 * The interface also allows for searching agreements based on various criteria.
 */
public interface AgreementService {
    /**
     * Retrieves a list of all agreements in the system.
     *
     * @return A list of all agreements.
     */
    List<Agreement> findAll();

    /**
     * Saves a new or existing agreement to the database.
     *
     * @param agreement The agreement to be saved.
     * @return The saved agreement.
     */
    Agreement save(Agreement agreement);

    /**
     * Creates a new agreement for the specified account.
     *
     * @param agreement The agreement to be created.
     * @param accountId The ID of the account associated with the agreement.
     * @return The created agreement.
     */
    Agreement createAgreement(Agreement agreement, UUID accountId);

    /**
     * Retrieves agreements based on the provided interest rate.
     *
     * @param interestRate The interest rate to search for.
     * @return A list of agreements with the specified interest rate.
     */
    List<Agreement> findAgreementByInterestRate(BigDecimal interestRate);

    /**
     * Updates an existing agreement with new data provided in the agreementFE parameter.
     *
     * @param id          The ID of the agreement to be updated.
     * @param agreementFE The updated agreement data.
     * @return The updated agreement.
     */
    Agreement editAgreement(UUID id, Agreement agreementFE);

    /**
     * Deletes an agreement with the specified ID.
     *
     * @param id The ID of the agreement to be deleted.
     * @return The deleted agreement.
     */
    Agreement deleteAgreementById(UUID id);

    /**
     * Deletes all agreements with the specified status.
     *
     * @param status The status of the agreements to be deleted.
     * @return A list of deleted agreements.
     */
    List<Agreement> deleteAgreementsByStatus(AgreementStatus status);

    /**
     * Changes the sum (amount) of an agreement with the specified ID.
     *
     * @param id     The ID of the agreement to be updated.
     * @param newSum The new sum (amount) to be set for the agreement.
     * @return The updated agreement.
     */
    Agreement changeSumById(UUID id, BigDecimal newSum);

    /**
     * Retrieves a list of all deleted agreements.
     *
     * @return A list of all deleted agreements.
     */
    List<Agreement> showDeleted();
}
