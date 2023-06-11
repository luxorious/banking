package com.banking.service.implementation;

import com.banking.entity.Manager;
import com.banking.entity.entityEnumerations.ManagerStatus;
import com.banking.repository.ManagerRepository;
import com.banking.service.interfaces.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService {

    private final ManagerRepository managerRepository;

    @Override
    public Manager createManager(Manager manager) {
        return managerRepository.save(manager);
    }

    @Override
    public Optional<Manager> findById(UUID uuid) {
        return managerRepository.findById(uuid);
    }

    @Override
    public Optional<Manager> findManagerByFirstNameAndLastName(String firstName, String lastName) {
        return managerRepository.findManagerByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public List<Manager> findManagerByStatus(ManagerStatus status) {
        return managerRepository.findManagerByStatus(status);
    }
}
