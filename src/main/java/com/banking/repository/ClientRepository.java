package com.banking.repository;

import com.banking.entity.Client;
import com.banking.entity.entityenumerations.ClientStatus;
import com.banking.entity.entityenumerations.DeletedStatus;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@NonNullApi
public interface ClientRepository extends JpaRepository<Client, UUID> {

    @Override
    Optional<Client> findById(UUID uuid);

    Optional<Client> findClientByFirstNameAndLastName(String firstName, String lastName);

    List<Client> findClientsByStatus(ClientStatus status);

    List<Client> findClientsByDeletedStatus(DeletedStatus deletedStatus);



}
