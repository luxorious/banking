package com.banking.repository;

import com.banking.entity.Credit;
import com.banking.entity.entityenumerations.CreditStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Repository interface for managing Credit entities.
 * This interface extends JpaRepository to provide basic CRUD operations for Credit entities.
 */
@Repository
public interface CreditRepository extends JpaRepository<Credit, UUID> {
    /**
     * Retrieves a list of credit entities based on the client ID.
     *
     * @param id The ID of the client to retrieve the credits for.
     * @return A list of credit entities associated with the specified client ID.
     */
    List<Credit> findAllByClientId(UUID id);

    /**
     * Retrieves a list of credit entities based on the credit status.
     *
     * @param creditStatus The status of the credits to retrieve.
     * @return A list of credit entities with the specified credit status.
     */
    List<Credit> findAllByCreditStatus(CreditStatus creditStatus);

    /**
     * Retrieves a list of credit entities based on the credit status and client ID.
     *
     * @param creditStatus The status of the credits to retrieve.
     * @param clientId     The ID of the client to retrieve the credits for.
     * @return A list of credit entities with the specified credit status and client ID.
     */
    List<Credit> findAllByCreditStatusAndClientId(CreditStatus creditStatus, UUID clientId);
}
