package com.banking.repository;

import com.banking.entity.Agreement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AgreementRepository extends JpaRepository<Agreement, UUID> {
//    @Override
//    Optional<Agreement> findById(UUID uuid);
//
    List<Agreement> findAgreementByInterestRate(Double interestRate);


}
