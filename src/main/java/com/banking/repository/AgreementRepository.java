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

@Repository
@NonNullApi
public interface AgreementRepository extends JpaRepository<Agreement, UUID> {
    Optional<Agreement> findById(UUID uuid);
    List<Agreement> findAgreementByInterestRate(BigDecimal interestRate);
    List<Agreement> findAgreementByIdAndInterestRate(UUID id, BigDecimal interestRate);
    List<Agreement> findAgreementsByIdAndStatus(UUID id, AgreementStatus status);
    List<Agreement> findAgreementsByStatus(AgreementStatus status);
    List<Agreement> findAgreementsByDeletedStatus(DeletedStatus status);

}
