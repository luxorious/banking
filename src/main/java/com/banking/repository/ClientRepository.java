package com.banking.repository;

import com.banking.entity.Agreement;
import com.banking.entity.Client;
import com.banking.entity.entityEnumerations.ClientStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {

    @Override
    Optional<Client> findById(UUID uuid);

    Optional<Client> findClientByFirstNameAndLastName(String firstName, String lastName);

    List<Client> findClientByStatus(ClientStatus status);


}