package com.banking.service.implementation;

import com.banking.entity.Client;
import com.banking.entity.entityenumerations.ClientStatus;
import com.banking.entity.entityenumerations.DeletedStatus;
import com.banking.repository.ClientRepository;
import com.banking.security.interfaces.AuthorisationService;
import com.banking.service.interfaces.ClientService;
import com.banking.service.interfaces.utility.Converter;
import com.banking.service.interfaces.utility.ValidatorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
/**
 * Service implementation for managing clients.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final Converter<Client> clientConverter;
    private final ValidatorService<Client> validatorService;
    private final AuthorisationService authorisationService;
    /**
     * Creates a new client and saves it to the repository.
     *
     * @param client The client to be created and saved.
     * @param managerId The UUID of the manager associated with the client.
     * @return The created and saved client.
     */
    @Override
    public Client createClient(Client client, UUID managerId) {
        Client dbClient = clientRepository.save(client);
        log.info("db client - " + dbClient.getId());
        authorisationService.createClient(dbClient);
        return dbClient;
    }
    /**
     * Finds a client by the specified UUID.
     *
     * @param uuid The UUID of the client to find.
     * @return The client with the specified UUID.
     */
    @Override
    public Client findById(UUID uuid) {
        return validatorService.checkEntity(clientRepository.findById(uuid));
    }
    /**
     * Finds a client by first name and last name.
     *
     * @param firstName The first name of the client to find.
     * @param lastName The last name of the client to find.
     * @return An optional containing the client with the specified first name and last name, or empty if not found.
     */
    @Override
    public Optional<Client> findClientByFirstNameAndLastName(String firstName, String lastName) {
        return clientRepository.findClientByFirstNameAndLastName(firstName, lastName);
    }

    /**
     * Finds clients by the specified status.
     *
     * @param status The ClientStatus to search for.
     * @return List of clients with the specified status.
     */
    @Override
    public List<Client> findClientsByStatus(ClientStatus status) {
        return clientRepository.findClientsByStatus(status);
    }
    /**
     * Edits an existing client with the specified ID using the information from the provided Client object.
     *
     * @param id The UUID of the client to edit.
     * @param clientFE The Client object containing the updated information.
     * @return The edited and saved client.
     */
    @Override
    public Client editClient(UUID id, Client clientFE) {
        Optional<Client> clientFromDB = clientRepository.findById(id);
        if (clientFromDB.isPresent()) {
            Client clientToUpdate = clientConverter.convertFields(clientFromDB.get(), clientFE);
            clientRepository.save(clientToUpdate);
            log.info("Client with client_ID: " + id + " updated successfully");
            return clientToUpdate;
        } else {
            log.info("Client with client_ID: " + id + " not found");//NOSONAR
            return new Client();
        }
    }

    /**
     * Soft deletes the client with the specified ID by setting its deleted status to "DELETED".
     *
     * @param id The UUID of the client to delete.
     * @return The deleted Client object.
     */
    @Override
    public Client deleteClientById(UUID id) {
        Optional<Client> deletedClient = clientRepository.findById(id);
        if (deletedClient.isPresent()) {
            Client client = deletedClient.get();
            client.setDeletedStatus(DeletedStatus.DELETED);
            clientRepository.save(client);
            log.info("delete account where UUID - " + id);
            return deletedClient.get();
        } else {
            log.info("Account not found!");
            return new Client();
        }
    }
    /**
     * Soft deletes multiple clients associated with the specified status by setting their deleted status to "DELETED".
     *
     * @param status The ClientStatus of the clients to delete.
     * @return List of deleted Client objects.
     */
    @Override
    public List<Client> deleteClientsByStatus(ClientStatus status) {
        List<Client> clients = clientRepository.findClientsByStatus(status);
        clients.forEach(client -> client.setDeletedStatus(DeletedStatus.DELETED));
        log.info("deleting clients where Account Status - " + status);
        return clients;
    }
    /**
     * Edits the email of the client with the specified ID.
     *
     * @param id The UUID of the client to edit.
     * @param eMail The new email to set for the client.
     * @return The edited and saved client.
     */
    @Override
    public Client editEmailById(UUID id, String eMail) {
        Optional<Client> clientFromDB = clientRepository.findById(id);
        if (clientFromDB.isPresent()) {
            Client client = clientFromDB.get();
            client.setEmail(eMail);
            clientRepository.save(client);
            log.info("Client's eMail successfully updated: " + id);
            return clientFromDB.get();
        } else {
            log.info("Client with client ID: " + id + " not found");
            return new Client();
        }
    }
    /**
     * Edits the phone of the client with the specified ID.
     *
     * @param id The UUID of the client to edit.
     * @param phone The new phone number to set for the client.
     * @return The edited and saved client.
     */
    @Override
    public Client editPhoneById(UUID id, String phone) {
        Optional<Client> clientFromDB = clientRepository.findById(id);
        if (clientFromDB.isPresent()) {
            clientFromDB.get().setPhone(phone);
            clientRepository.save(clientFromDB.get());
            log.info("Client's phone successfully updated: " + id);
            return clientFromDB.get();
        } else {
            log.info("Client with client ID: " + id + " not found");
            return new Client();
        }
    }
    /**
     * Finds clients by the specified deleted status.
     *
     * @param deletedStatus The DeletedStatus to search for.
     * @return List of clients with the specified deleted status.
     */
    @Override
    public List<Client> findClientsByDeletedStatus(DeletedStatus deletedStatus) {
        return clientRepository.findClientsByDeletedStatus(deletedStatus);
    }
    /**
     * Restores the client with the specified ID by setting its deleted status to ACTIVE.
     *
     * @param id The UUID of the client to restore.
     * @return The restored Client object.
     */
    @Override
    public Client restoreById(UUID id) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()) {
            Client toRestore = client.get();
            toRestore.setDeletedStatus(DeletedStatus.ACTIVE);
            clientRepository.save(toRestore);
            log.info("client restored");
            return toRestore;
        } else {
            log.error("client not found");
            return new Client();
        }
    }
    /**
     * Restores all deleted clients by setting their deleted status to ACTIVE.
     *
     * @return List of restored Client objects.
     */
    @Override
    public List<Client> restoreAll() {
        List<Client> clients = clientRepository.findClientsByDeletedStatus(DeletedStatus.DELETED);
        clients.forEach(client -> client.setDeletedStatus(DeletedStatus.ACTIVE));
        clientRepository.saveAll(clients);
        log.info("all deleted Clients are restored!");
        return clients;
    }
    /**
     * Saves a client to the repository.
     *
     * @param client The client to be saved.
     * @return The saved client.
     */
    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }
}
