package com.banking.repository;

import com.banking.entity.Credit;
import com.banking.entity.entityenumerations.CreditStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CreditRepository extends JpaRepository<Credit, UUID> {

    List<Credit> findAllByClientId(UUID id);

    List<Credit> findAllByCreditStatus(CreditStatus creditStatus);
}
