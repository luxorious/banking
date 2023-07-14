package com.banking.security.repository;

import com.banking.security.Authorisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthorisationRepository extends JpaRepository<Authorisation, UUID> {
    Optional<Authorisation> findByLogin(String login);

    Boolean existsByLogin(String login);
//    Authorisation findAuthorisationByClient_Id(UUID id);
}
