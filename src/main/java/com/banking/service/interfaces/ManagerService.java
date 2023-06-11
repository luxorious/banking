package com.banking.service.interfaces;

import com.banking.entity.Manager;
import com.banking.entity.entityEnumerations.ManagerStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ManagerService {
    Manager createManager(Manager manager);

    Optional<Manager> findById(UUID uuid);

    Optional<Manager> findManagerByFirstNameAndLastName(String firstName, String lastName);

    List<Manager> findManagerByStatus(ManagerStatus status);
}
