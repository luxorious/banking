package com.banking.service.component.implementation;

import com.banking.entity.Manager;
import com.banking.entity.entityenumerations.Role;
import com.banking.service.component.interfaces.RoleManagementService;
import com.banking.service.interfaces.ManagerService;
import com.banking.service.interfaces.utility.GetEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * This class provides the implementation for the RoleManagementService interface,
 * which is responsible for setting the role of a manager with the specified ID.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class RoleManagementServiceImpl implements RoleManagementService {

    private final ManagerService managerService;
    private final GetEntity<Manager> getManager;

    /**
     * Set the role of a manager with the specified ID.
     *
     * @param id   The ID of the manager.
     * @param role The role to be set for the manager.
     * @return The manager with the updated role.
     */
    @Override
    public Manager setRole(UUID id, Role role) {
        Manager manager = getManager.getEntity(managerService.findById(id));
        manager.setRole(role);
        return managerService.createManager(manager, role);
    }
}
