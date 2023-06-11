package com.banking.service.interfaces;

import com.banking.entity.Client;
import com.banking.entity.entityEnumerations.ClientStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClientService {

    Client createClient(Client client);

    Optional<Client> findById(UUID uuid);

    Optional<Client> findClientByFirstNameAndLastName(String firstName, String lastName);

    List<Client> findClientByStatus(ClientStatus status);
}
