package com.banking.service.component.interfaces;

import com.banking.entity.Manager;
import com.banking.entity.entityenumerations.Role;

import java.util.UUID;

/**
 * This interface defines the contract for a role management service.
 * Implementing classes are responsible for managing roles for managers in the banking system.
 */
public interface RoleManagementService {
    /**
     * Set the role of a manager with the specified ID.
     *
     * @param id   The ID of the manager.
     * @param role The role to be set for the manager.
     * @return The manager with the updated role.
     */
    Manager setRole(UUID id, Role role);
}

