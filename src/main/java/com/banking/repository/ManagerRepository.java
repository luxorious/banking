package com.banking.repository;

import com.banking.entity.Manager;
import com.banking.entity.entityenumerations.DeletedStatus;
import com.banking.entity.entityenumerations.ManagerStatus;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for managing Manager entities.
 * This interface extends JpaRepository to provide basic CRUD operations for Manager entities.
 */
@Repository
@NonNullApi
public interface ManagerRepository extends JpaRepository<Manager, UUID> {

    /**
     * Retrieves an optional Manager entity based on the manager ID.
     *
     * @param uuid The ID of the manager to retrieve.
     * @return An optional Manager entity with the specified ID.
     */
    Optional<Manager> findById(UUID uuid);

    /**
     * Retrieves an optional Manager entity based on the first name and last name.
     *
     * @param firstName The first name of the manager.
     * @param lastName  The last name of the manager.
     * @return An optional Manager entity with the specified first name and last name.
     */
    Optional<Manager> findManagerByFirstNameAndLastName(String firstName, String lastName);

    /**
     * Retrieves a list of Manager entities based on the manager status.
     *
     * @param status The status of the managers to retrieve.
     * @return A list of Manager entities with the specified status.
     */
    List<Manager> findManagersByStatus(ManagerStatus status);

    /**
     * Retrieves a list of Manager entities based on the deleted status.
     *
     * @param deletedStatus The deleted status of the managers to retrieve.
     * @return A list of Manager entities with the specified deleted status.
     */
    List<Manager> findManagersByDeletedStatus(DeletedStatus deletedStatus);

    /**
     * Retrieves a list of Manager entities based on the creation date.
     *
     * @param date The creation date of the managers to retrieve.
     * @return A list of Manager entities created on the specified date.
     */
    List<Manager> findManagersByCreatedAt(Timestamp date);

}
