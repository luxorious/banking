package com.banking.service.component.interfaces;

import com.banking.entity.Manager;
import com.banking.entity.entityenumerations.Role;

import java.util.UUID;

public interface RoleManagementService {
    Manager setRole(UUID id, Role role);
}

