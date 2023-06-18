package com.banking.service.interfaces;

import com.banking.entity.Client;
import com.banking.entity.entityEnumerations.ClientStatus;
import com.banking.entity.entityEnumerations.DeletedStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClientService {

    Client createClient(Client client);

    Optional<Client> findById(UUID uuid);

    Optional<Client> findClientByFirstNameAndLastName(String firstName, String lastName);

    List<Client> findClientsByStatus(ClientStatus status);

    Client editClient(UUID id, Client clientFE);

    Client deleteClientById(UUID id);


    List<Client> deleteClientsByStatus(ClientStatus status);

    Client editEmailById(UUID id, String eMail);

    Client editPhoneById(UUID id, String phone);

    //Admin
    List<Client> findClientsByDeletedStatus(DeletedStatus deletedStatus);

    Client restoreById(UUID id);
    List<Client> restoreAll();
}



