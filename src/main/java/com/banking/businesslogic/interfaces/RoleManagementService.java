package com.banking.businesslogic.interfaces;

import com.banking.entity.Manager;
import com.banking.entity.entityEnumerations.Role;

import java.util.UUID;

public interface RoleManagementService {
    Manager setRole(UUID id, Role role);
}

