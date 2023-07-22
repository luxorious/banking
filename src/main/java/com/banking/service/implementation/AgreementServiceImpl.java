package com.banking.service.implementation;

import com.banking.entity.Agreement;
import com.banking.entity.entityenumerations.AgreementStatus;
import com.banking.entity.entityenumerations.DeletedStatus;
import com.banking.repository.AgreementRepository;
import com.banking.service.implementation.utility.AgreementConverterImpl;
import com.banking.service.interfaces.AgreementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service implementation for managing agreements.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AgreementServiceImpl implements AgreementService {

    private final AgreementRepository agreementRepository;
    private final AgreementConverterImpl agreementConverter;

    /**
     * Retrieves a list of all active agreements.
     *
     * @return List of all active agreements.
     */
    @Override
    public List<Agreement> findAll() {
        return agreementRepository.findAgreementsByDeletedStatus(DeletedStatus.ACTIVE);
    }

    /**
     * Saves an agreement to the repository.
     *
     * @param agreement The agreement to be saved.
     * @return The saved agreement.
     */
    @Override
    public Agreement save(Agreement agreement) {
        return agreementRepository.save(agreement);
    }

    /**
     * Creates an agreement associated with the given account ID and saves it to the repository.
     *
     * @param agreement The agreement to be created and saved.
     * @param accountId The UUID of the account to which the agreement is associated.
     * @return The created and saved agreement.
     */
    @Override
    public Agreement createAgreement(Agreement agreement, UUID accountId) {
        agreement.setAccountId(accountId);
        return agreementRepository.save(agreement);
    }

    /**
     * Finds agreements with the specified interest rate.
     *
     * @param interestRate The interest rate to search for.
     * @return List of agreements with the specified interest rate.
     */
    @Override
    public List<Agreement> findAgreementByInterestRate(BigDecimal interestRate) {
        return agreementRepository.findAgreementByInterestRate(interestRate);
    }

    /**
     * Edits an existing agreement with the specified ID using the information from the provided Agreement object.
     *
     * @param id          The UUID of the agreement to edit.
     * @param agreementFE The Agreement object containing the updated information.
     * @return The edited and saved agreement.
     */
    @Override
    public Agreement editAgreement(UUID id, Agreement agreementFE) {
        Optional<Agreement> agreement = agreementRepository.findById(id);
        if (agreement.isPresent()) {
            Agreement changedAgreement = agreementConverter.convertFields(agreement.get(), agreementFE);
            agreementRepository.save(changedAgreement);
            log.info("agreement changed!");
            return changedAgreement;
        } else {
            log.error("Agreement not found! with this id" + id);
            return new Agreement();
        }
    }

    /**
     * Soft deletes the agreement with the specified ID by setting its deleted status to "DELETED".
     *
     * @param id The UUID of the agreement to delete.
     * @return The deleted Agreement object.
     */
    @Override
    public Agreement deleteAgreementById(UUID id) {
        Optional<Agreement> agreement = agreementRepository.findById(id);
        if (agreement.isPresent()) {
            Agreement changedAgreement = agreement.get();
            changedAgreement.setDeletedStatus(DeletedStatus.DELETED);
            agreementRepository.save(changedAgreement);
            log.info("agreement deleted!");
            return changedAgreement;
        } else {
            log.error("Agreement not found!");
            return new Agreement();
        }
    }

    /**
     * Soft deletes multiple agreements associated with the specified status by setting their deleted status to "DELETED".
     *
     * @param status The AgreementStatus of the agreements to delete.
     * @return List of deleted Agreement objects.
     */
    @Override
    public List<Agreement> deleteAgreementsByStatus(AgreementStatus status) {
        List<Agreement> agreementsTODeleting = agreementRepository.findAgreementsByStatus(status);
        for (Agreement agreement : agreementsTODeleting) {
            agreement.setDeletedStatus(DeletedStatus.DELETED);
        }
        agreementRepository.saveAll(agreementsTODeleting);
        return agreementsTODeleting;
    }

    /**
     * Changes the sum of an agreement with the specified ID.
     *
     * @param id     The UUID of the agreement to edit.
     * @param newSum The new sum to set for the agreement.
     * @return The edited and saved agreement.
     */
    @Override
    public Agreement changeSumById(UUID id, BigDecimal newSum) {
        Optional<Agreement> agreement = agreementRepository.findById(id);
        if (agreement.isPresent()) {
            Agreement changedAgreement = agreement.get();
            changedAgreement.setSum(newSum);
            log.info("agreement changed!");
            agreementRepository.save(agreement.get());
            return changedAgreement;
        } else {
            log.error("Agreement not found!");
            return new Agreement();
        }
    }

    /**
     * Retrieves a list of all deleted agreements.
     *
     * @return List of all deleted agreements.
     */
    @Override
    public List<Agreement> showDeleted() {
        log.info("Show all deleted clients:");
        return agreementRepository.findAgreementsByDeletedStatus(DeletedStatus.DELETED);
    }
}
