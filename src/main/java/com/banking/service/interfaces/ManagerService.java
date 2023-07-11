package com.banking.service.interfaces;

import com.banking.entity.Manager;
import com.banking.entity.entityenumerations.ManagerStatus;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ManagerService {
    Manager save(Manager manager);

    Manager createManager(Manager manager);

    Optional<Manager> findById(UUID uuid);

    Optional<Manager> findManagerByFirstNameAndLastName(String firstName, String lastName);

    List<Manager> findManagersByStatus(ManagerStatus status);

    List<Manager> findAll();

    List<Manager> findManagersByCreatedAt(Timestamp dateCreation);

    Boolean updateManagerById(UUID id, Manager managerFromFE);

    Manager updateStatusById(UUID id, ManagerStatus status);

    Manager deleteManagerById(UUID id);

    List<Manager> deleteManagersByStatus(ManagerStatus status);

    Manager restoreById(UUID id);

    List<Manager> restoreAll();

    List<Manager> showAllDeleted();

    List<Manager> showAllManagersForAdmin();
}