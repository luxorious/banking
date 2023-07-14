package com.banking.service.component.implementation;

import com.banking.service.component.interfaces.RoleManagementService;
import com.banking.entity.Manager;
import com.banking.entity.entityenumerations.Role;
import com.banking.service.interfaces.ManagerService;
import com.banking.service.interfaces.utility.GetEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleManagementServiceImpl implements RoleManagementService {

    private final ManagerService managerService;
    private final GetEntity<Manager> getManager;

    @Override
    public Manager setRole(UUID id, Role role) {
        Manager manager = getManager.getEntity(managerService.findById(id));
        manager.setRole(role);
        return managerService.createManager(manager, role);
    }
}
