package com.banking.repository;

import com.banking.entity.Agreement;
import com.banking.entity.entityenumerations.AgreementStatus;
import com.banking.entity.entityenumerations.DeletedStatus;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for managing Agreement entities.
 * This interface extends JpaRepository to provide basic CRUD operations for Agreement entities.
 */
@Repository
@NonNullApi
public interface AgreementRepository extends JpaRepository<Agreement, UUID> {

    /**
     * Find an Agreement by its ID.
     *
     * @param uuid The ID of the Agreement to find.
     * @return An optional containing the found Agreement, or an empty optional if not found.
     */
    Optional<Agreement> findById(UUID uuid);

    /**
     * Find Agreements by their interest rate.
     *
     * @param interestRate The interest rate of the Agreements to find.
     * @return A list of Agreements that match the provided interest rate.
     */
    List<Agreement> findAgreementByInterestRate(BigDecimal interestRate);

    /**
     * Find Agreements by their ID and interest rate.
     *
     * @param id           The ID of the Agreements to find.
     * @param interestRate The interest rate of the Agreements to find.
     * @return A list of Agreements that match the provided ID and interest rate.
     */
    List<Agreement> findAgreementByIdAndInterestRate(UUID id, BigDecimal interestRate);

    /**
     * Find Agreements by their ID and AgreementStatus.
     *
     * @param id     The ID of the Agreements to find.
     * @param status The AgreementStatus of the Agreements to find.
     * @return A list of Agreements that match the provided ID and AgreementStatus.
     */
    List<Agreement> findAgreementsByIdAndStatus(UUID id, AgreementStatus status);

    /**
     * Find Agreements by their AgreementStatus.
     *
     * @param status The AgreementStatus of the Agreements to find.
     * @return A list of Agreements that match the provided AgreementStatus.
     */
    List<Agreement> findAgreementsByStatus(AgreementStatus status);

    /**
     * Find Agreements by their DeletedStatus.
     *
     * @param status The DeletedStatus of the Agreements to find.
     * @return A list of Agreements that match the provided DeletedStatus.
     */
    List<Agreement> findAgreementsByDeletedStatus(DeletedStatus status);

}
