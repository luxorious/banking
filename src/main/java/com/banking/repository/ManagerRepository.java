package com.banking.repository;

import com.banking.entity.Agreement;
import com.banking.entity.Manager;
import com.banking.entity.entityEnumerations.ManagerStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, UUID> {

    @Override
    Optional<Manager> findById(UUID uuid);

    Optional<Manager> findManagerByFirstNameAndLastName(String firstName, String lastName);

    List<Manager> findManagerByStatus(ManagerStatus status);

}