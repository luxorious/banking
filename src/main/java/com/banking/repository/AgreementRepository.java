package com.banking.repository;

import com.banking.entity.Agreement;
import com.banking.entity.entityEnumerations.AgreementStatus;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@NonNullApi
public interface AgreementRepository extends JpaRepository<Agreement, UUID> {
    Optional<Agreement> findById(UUID uuid);

    List<Agreement> findAgreementByInterestRate(Double interestRate);

    List<Agreement> findAgreementsByStatus(AgreementStatus status);

}
