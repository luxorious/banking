package com.banking.service.interfaces;

import com.banking.entity.Manager;
import com.banking.entity.entityenumerations.ManagerStatus;
import com.banking.entity.entityenumerations.Role;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * The ManagerService interface defines the contract for managing managers in the banking system.
 * It provides methods for creating, retrieving, updating, and deleting managers, as well as restoring deleted managers.
 */
public interface ManagerService {
    /**
     * Creates a new manager with the specified role and saves it to the database.
     *
     * @param manager The manager to be created.
     * @return The created manager.
     */
    Manager createManager(Manager manager);

    /**
     * Retrieves the manager with the specified ID.
     *
     * @param uuid The ID of the manager to be retrieved.
     * @return An Optional containing the manager if found, otherwise an empty Optional.
     */
    Optional<Manager> findById(UUID uuid);

    /**
     * Retrieves the manager with the specified first name and last name.
     *
     * @param firstName The first name of the manager to be retrieved.
     * @param lastName  The last name of the manager to be retrieved.
     * @return An Optional containing the manager if found, otherwise an empty Optional.
     */
    Optional<Manager> findManagerByFirstNameAndLastName(String firstName, String lastName);

    /**
     * Retrieves a list of managers with the specified status.
     *
     * @param status The status of the managers to be retrieved (e.g., active, inactive).
     * @return The list of managers with the specified status.
     */
    List<Manager> findManagersByStatus(ManagerStatus status);

    /**
     * Retrieves a list of all managers in the system.
     *
     * @return The list of all managers.
     */
    List<Manager> findAll();

    /**
     * Retrieves a list of managers created on or after the specified date.
     *
     * @param dateCreation The date on or after which the managers were created.
     * @return The list of managers created on or after the specified date.
     */
    List<Manager> findManagersByCreatedAt(Timestamp dateCreation);

    /**
     * Updates the information of the manager with the specified ID using the provided manager data from the front-end.
     *
     * @param id            The ID of the manager to be updated.
     * @param managerFromFE The manager data from the front-end to update the existing manager.
     * @return True if the manager was successfully updated, false otherwise.
     */
    Boolean updateManagerById(UUID id, Manager managerFromFE);

    /**
     * Updates the status of the manager with the specified ID.
     *
     * @param id     The ID of the manager to update the status.
     * @param status The new status of the manager (e.g., active, inactive).
     * @return The updated manager with the new status.
     */
    Manager updateStatusById(UUID id, ManagerStatus status);

    /**
     * Deletes the manager with the specified ID by setting its deleted status to "DELETED".
     *
     * @param id The ID of the manager to be deleted.
     * @return The deleted manager.
     */
    Manager deleteManagerById(UUID id);

    /**
     * Deletes all managers with the specified status by setting their deleted status to "DELETED".
     *
     * @param status The status of the managers to be deleted (e.g., active, inactive).
     * @return The list of deleted managers.
     */
    List<Manager> deleteManagersByStatus(ManagerStatus status);

    /**
     * Restores the manager with the specified ID by setting its deleted status to ACTIVE.
     *
     * @param id The ID of the manager to be restored.
     * @return The restored manager.
     */
    Manager restoreById(UUID id);

    /**
     * Restores all managers with a deleted status by setting their deleted status to ACTIVE.
     *
     * @return The list of restored managers.
     */
    List<Manager> restoreAll();

    /**
     * Retrieves a list of all managers with a deleted status (DELETED).
     *
     * @return The list of all managers with a deleted status.
     */
    List<Manager> showAllDeleted();

    /**
     * Retrieves a list of all managers for administrative purposes, including both active and deleted managers.
     *
     * @return The list of all managers for administrative purposes.
     */
    List<Manager> showAllManagersForAdmin();
}