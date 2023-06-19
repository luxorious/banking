package com.banking.service.implementation;

import com.banking.entity.Agreement;
import com.banking.entity.entityEnumerations.AgreementStatus;
import com.banking.entity.entityEnumerations.DeletedStatus;
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

@Service
@RequiredArgsConstructor
@Slf4j
public class AgreementServiceImpl implements AgreementService {

    private final AgreementRepository agreementRepository;
    private final AgreementConverterImpl agreementConverter;

    @Override
    public List<Agreement> findAll() {
        return agreementRepository.findAgreementsByDeletedStatus(DeletedStatus.ACTIVE);
    }

    @Override
    public Agreement createAgreement(Agreement agreement) {
        return agreementRepository.save(agreement);
    }

    @Override
    public List<Agreement> findAgreementByInterestRate(BigDecimal interestRate) {
        return agreementRepository.findAgreementByInterestRate(interestRate);
    }

    @Override
    public Agreement editAgreement(UUID id, Agreement agreementFE) {
        Optional<Agreement> agreement = agreementRepository.findById(id);
        if (agreement.isPresent()){
            Agreement changedAgreement = agreementConverter.convertFields(agreement.get(), agreementFE);
            agreementRepository.save(changedAgreement);
            log.info("agreement changed!");
            return changedAgreement;
        } else {
            log.error("Agreement not found! with this id" + id);
            return new Agreement();
        }
    }

    @Override
    public Agreement deleteAgreementById(UUID id) {
        Optional<Agreement> agreement = agreementRepository.findById(id);
        if (agreement.isPresent()){
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

    @Override
    public List<Agreement> deleteAgreementsByStatus(AgreementStatus status) {
        List<Agreement> agreementsTODeleting = agreementRepository.findAgreementsByStatus(status);
        for (Agreement agreement : agreementsTODeleting){
            agreement.setDeletedStatus(DeletedStatus.DELETED);
        }
        agreementRepository.saveAll(agreementsTODeleting);
        return agreementsTODeleting;
    }

    @Override
    public Agreement changeSumById(UUID id, BigDecimal newSum) {
        Optional<Agreement> agreement = agreementRepository.findById(id);
        if (agreement.isPresent()){
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

    @Override
    public List<Agreement> showDeleted() {
        log.info("Show all deleted clients:");
        return agreementRepository.findAgreementsByDeletedStatus(DeletedStatus.DELETED);
    }
}
