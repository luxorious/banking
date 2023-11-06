package com.banking.service.implementation;

import com.banking.entity.Manager;
import com.banking.entity.entityenumerations.DeletedStatus;
import com.banking.entity.entityenumerations.ManagerStatus;
import com.banking.entity.entityenumerations.Role;
import com.banking.repository.ManagerRepository;
import com.banking.security.interfaces.AuthorisationService;
import com.banking.service.interfaces.ManagerService;
import com.banking.service.interfaces.utility.Converter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service implementation for managing managers.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ManagerServiceImpl implements ManagerService {

    private final ManagerRepository managerRepository;
    private final Converter<Manager> managerConverter;
    private final AuthorisationService authorisationService;

    /**
     * Creates a new manager with the specified role.
     *
     * @param manager The Manager object containing manager-related information.
     * @return The created and saved Manager object.
     */
    @Override
    public Manager createManager(Manager manager) {
        Manager dbManager = managerRepository.save(manager);
        authorisationService.createManager(dbManager);
        return dbManager;
    }

    /**
     * Finds the manager with the specified ID.
     *
     * @param uuid The UUID of the manager to find.
     * @return The optional Manager object with the specified ID.
     */
    @Override
    public Optional<Manager> findById(UUID uuid) {
        return managerRepository.findById(uuid);
    }

    /**
     * Finds the manager with the specified first name and last name.
     *
     * @param firstName The first name of the manager to find.
     * @param lastName  The last name of the manager to find.
     * @return The optional Manager object with the specified first name and last name.
     */
    @Override
    public Optional<Manager> findManagerByFirstNameAndLastName(String firstName, String lastName) {
        return managerRepository.findManagerByFirstNameAndLastName(firstName, lastName);
    }

    /**
     * Finds all managers with the specified status.
     *
     * @param status The status of the managers to find.
     * @return The list of managers with the specified status.
     */
    @Override
    public List<Manager> findManagersByStatus(ManagerStatus status) {
        return managerRepository.findManagersByStatus(status);
    }

    /**
     * Finds all active managers.
     *
     * @return The list of all active managers.
     */
    @Override
    public List<Manager> findAll() {
        return managerRepository.findManagersByDeletedStatus(DeletedStatus.ACTIVE);
    }

    /**
     * Finds all managers created at the specified timestamp.
     *
     * @param dateCreation The timestamp at which managers were created.
     * @return The list of managers created at the specified timestamp.
     */
    @Override
    public List<Manager> findManagersByCreatedAt(Timestamp dateCreation) {
        return managerRepository.findManagersByCreatedAt(dateCreation);
    }

    /**
     * Updates the manager with the specified ID using the data from the provided Manager object.
     *
     * @param id            The UUID of the manager to update.
     * @param managerFromFE The updated Manager object containing the new manager information.
     * @return True if the manager was updated successfully, false otherwise.
     */
    @Override
    public Boolean updateManagerById(UUID id, Manager managerFromFE) {
        Optional<Manager> managerFromDB = managerRepository.findById(id);
        if (managerFromDB.isPresent()) {
            Manager manager = managerConverter.convertFields(managerFromDB.get(), managerFromFE);
            managerRepository.save(manager);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Updates the status of the manager with the specified ID.
     *
     * @param id     The UUID of the manager to update.
     * @param status The new status of the manager.
     * @return The updated Manager object.
     */
    @Override
    public Manager updateStatusById(UUID id, ManagerStatus status) {
        Optional<Manager> manager = managerRepository.findById(id);
        if (manager.isPresent()) {
            Manager changedManager = manager.get();
            changedManager.setStatus(status);
            managerRepository.save(changedManager);
            log.info("manager status updated");
            return changedManager;
        } else {
            log.error("manager not found!");
            return new Manager();
        }
    }

    /**
     * Deletes the manager with the specified ID.
     *
     * @param id The UUID of the manager to delete.
     * @return The deleted Manager object.
     */
    @Override
    public Manager deleteManagerById(UUID id) {
        Optional<Manager> manager = managerRepository.findById(id);
        if (manager.isPresent()) {
            Manager changedManager = manager.get();
            changedManager.setDeletedStatus(DeletedStatus.DELETED);
            managerRepository.save(changedManager);
            log.info("manager deleted!");
            return changedManager;
        } else {
            log.error("manager not found!");
            return new Manager();
        }
    }

    /**
     * Deletes all managers with the specified status.
     *
     * @param status The status of the managers to delete.
     * @return The list of deleted managers.
     */
    @Override
    public List<Manager> deleteManagersByStatus(ManagerStatus status) {
        List<Manager> managersToDelete = managerRepository.findManagersByStatus(status);
        for (Manager manager : managersToDelete) {
            manager.setDeletedStatus(DeletedStatus.DELETED);
        }
        return managersToDelete;
    }

    /**
     * Restores the manager with the specified ID.
     *
     * @param id The UUID of the manager to restore.
     * @return The restored Manager object.
     */
    @Override
    public Manager restoreById(UUID id) {
        Optional<Manager> managerFromDB = managerRepository.findById(id);
        if (managerFromDB.isPresent()) {
            Manager manager = managerFromDB.get();
            manager.setDeletedStatus(DeletedStatus.ACTIVE);
            return manager;
        } else {
            return new Manager();
        }
    }

    /**
     * Restores all deleted managers.
     *
     * @return The list of restored managers.
     */
    @Override
    public List<Manager> restoreAll() {
        List<Manager> managersToRestore = managerRepository.findManagersByDeletedStatus(DeletedStatus.DELETED);
        for (Manager manager : managersToRestore) {
            manager.setDeletedStatus(DeletedStatus.ACTIVE);
        }
        return managersToRestore;
    }

    /**
     * Shows all deleted managers.
     *
     * @return The list of all deleted managers.
     */
    @Override
    public List<Manager> showAllDeleted() {
        return managerRepository.findManagersByDeletedStatus(DeletedStatus.DELETED);
    }

    /**
     * Shows all managers for admin purposes.
     *
     * @return The list of all managers for admin purposes.
     */
    @Override
    public List<Manager> showAllManagersForAdmin() {
        return managerRepository.findAll();
    }
}
