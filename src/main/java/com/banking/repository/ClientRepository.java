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

/**
 * Repository interface for managing Client entities.
 * This interface extends JpaRepository to provide basic CRUD operations for Client entities.
 */
@Repository
@NonNullApi
public interface ClientRepository extends JpaRepository<Client, UUID> {

    /**
     * Retrieves a client entity by its unique ID.
     *
     * @param uuid The ID of the client entity to retrieve.
     * @return An Optional containing the client entity if found, or an empty Optional if not found.
     */
    @Override
    Optional<Client> findById(UUID uuid);

    /**
     * Retrieves a client entity by its first name and last name.
     *
     * @param firstName The first name of the client.
     * @param lastName  The last name of the client.
     * @return An Optional containing the client entity if found, or an empty Optional if not found.
     */
    Optional<Client> findClientByFirstNameAndLastName(String firstName, String lastName);

    /**
     * Retrieves a list of client entities based on their status.
     *
     * @param status The status of the clients to retrieve.
     * @return A list of client entities with the specified status.
     */
    List<Client> findClientsByStatus(ClientStatus status);

    /**
     * Retrieves a list of client entities based on their deleted status.
     *
     * @param deletedStatus The deleted status of the clients to retrieve.
     * @return A list of client entities with the specified deleted status.
     */
    List<Client> findClientsByDeletedStatus(DeletedStatus deletedStatus);

}
