package com.banking.service.implementation;

import com.banking.entity.Manager;
import com.banking.entity.entityenumerations.DeletedStatus;
import com.banking.entity.entityenumerations.ManagerStatus;
import com.banking.entity.entityenumerations.Role;
import com.banking.repository.ManagerRepository;
import com.banking.security.interfaces.AuthorisationService;
import com.banking.service.interfaces.ManagerService;
import com.banking.service.interfaces.utility.Converter;
import com.banking.service.mailservice.MailSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ManagerServiceImpl implements ManagerService {

    private final ManagerRepository managerRepository;
    private final Converter<Manager> managerConverter;
private final AuthorisationService authorisationService;


    @Override
    public Manager createManager(Manager manager, Role role) {
        Manager dbManager = managerRepository.save(manager);
        authorisationService.createManager(dbManager, role);
        return dbManager;
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
    public List<Manager> findManagersByStatus(ManagerStatus status) {
        return managerRepository.findManagersByStatus(status);
    }

    @Override
    public List<Manager> findAll() {
        return managerRepository.findManagersByDeletedStatus(DeletedStatus.ACTIVE);
    }

    @Override
    public List<Manager> findManagersByCreatedAt(Timestamp dateCreation) {
        return managerRepository.findManagersByCreatedAt(dateCreation);
    }

    @Override
    public Boolean updateManagerById(UUID id, Manager managerFromFE) {
        Optional<Manager> managerFromDB = managerRepository.findById(id);
        if (managerFromDB.isPresent()){
            Manager manager = managerConverter.convertFields(managerFromDB.get(), managerFromFE);
            managerRepository.save(manager);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Manager updateStatusById(UUID id, ManagerStatus status) {
        Optional<Manager> manager = managerRepository.findById(id);
        if (manager.isPresent()){
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

    @Override
    public Manager deleteManagerById(UUID id) {
        Optional<Manager> manager = managerRepository.findById(id);
        if (manager.isPresent()){
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

    @Override
    public List<Manager> deleteManagersByStatus(ManagerStatus status) {
        List<Manager> managersToDelete = managerRepository.findManagersByStatus(status);
        for (Manager manager : managersToDelete){
            manager.setDeletedStatus(DeletedStatus.DELETED);
        }
        return managersToDelete;
    }

    @Override
    public Manager restoreById(UUID id) {
        Optional<Manager> managerFromDB = managerRepository.findById(id);
        if (managerFromDB.isPresent()){
            Manager manager = managerFromDB.get();
            manager.setDeletedStatus(DeletedStatus.ACTIVE);
            return manager;
        } else {
            return new Manager();
        }
    }

    @Override
    public List<Manager> restoreAll() {
        List<Manager> managersToRestore = managerRepository.findManagersByDeletedStatus(DeletedStatus.DELETED);
        for (Manager manager : managersToRestore){
            manager.setDeletedStatus(DeletedStatus.ACTIVE);
        }
        return managersToRestore;
    }

    @Override
    public List<Manager> showAllDeleted() {
        return managerRepository.findManagersByDeletedStatus(DeletedStatus.DELETED);
    }

    @Override
    public List<Manager> showAllManagersForAdmin() {
        return managerRepository.findAll();
    }
}
