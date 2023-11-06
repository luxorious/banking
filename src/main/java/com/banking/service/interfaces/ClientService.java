package com.banking.service.interfaces;

import com.banking.entity.Client;
import com.banking.entity.entityenumerations.ClientStatus;
import com.banking.entity.entityenumerations.DeletedStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * The ClientService interface defines the contract for managing clients in the banking system.
 * It provides methods for creating, retrieving, updating, and deleting client information.
 * The interface also allows for searching clients based on various criteria.
 */
public interface ClientService {
    /**
     * Creates a new client with the provided information and associates it with the given manager.
     *
     * @param client    The client to be created.
     * @param managerId The ID of the manager associated with the client.
     * @return The created client.
     */
    Client createClient(Client client, UUID managerId);

    /**
     * Retrieves a client with the specified UUID.
     *
     * @param uuid The UUID of the client to be retrieved.
     * @return The client with the specified UUID, if found.
     */
    Client findById(UUID uuid);

    /**
     * Retrieves a client based on the first name and last name.
     *
     * @param firstName The first name of the client.
     * @param lastName  The last name of the client.
     * @return An Optional containing the client with the specified first name and last name, if found.
     */
    Optional<Client> findClientByFirstNameAndLastName(String firstName, String lastName);

    /**
     * Retrieves a list of clients with the specified status.
     *
     * @param status The status of the clients to be retrieved.
     * @return A list of clients with the specified status.
     */
    List<Client> findClientsByStatus(ClientStatus status);

    /**
     * Updates an existing client with new data provided in the clientFE parameter.
     *
     * @param id       The ID of the client to be updated.
     * @param clientFE The updated client data.
     * @return The updated client.
     */
    Client editClient(UUID id, Client clientFE);

    /**
     * Deletes a client with the specified ID.
     *
     * @param id The ID of the client to be deleted.
     * @return The deleted client.
     */
    Client deleteClientById(UUID id);

    /**
     * Deletes all clients with the specified status.
     *
     * @param status The status of the clients to be deleted.
     * @return A list of deleted clients.
     */
    List<Client> deleteClientsByStatus(ClientStatus status);

    /**
     * Updates the email of an existing client with the specified ID.
     *
     * @param id    The ID of the client to be updated.
     * @param eMail The new email to be set for the client.
     * @return The updated client.
     */
    Client editEmailById(UUID id, String eMail);

    /**
     * Updates the phone number of an existing client with the specified ID.
     *
     * @param id    The ID of the client to be updated.
     * @param phone The new phone number to be set for the client.
     * @return The updated client.
     */
    Client editPhoneById(UUID id, String phone);

    /**
     * Retrieves a list of clients with the specified deleted status.
     *
     * @param deletedStatus The deleted status of the clients to be retrieved.
     * @return A list of clients with the specified deleted status.
     */
    List<Client> findClientsByDeletedStatus(DeletedStatus deletedStatus);

    /**
     * Restores a deleted client with the specified ID.
     *
     * @param id The ID of the client to be restored.
     * @return The restored client.
     */
    Client restoreById(UUID id);

    /**
     * Restores all deleted clients.
     *
     * @return A list of all restored clients.
     */
    List<Client> restoreAll();

    /**
     * Saves a new or existing client to the database.
     *
     * @param client The client to be saved.
     * @return The saved client.
     */
    Client save(Client client);
}