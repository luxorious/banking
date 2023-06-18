package com.banking.repository;

import com.banking.entity.Manager;
import com.banking.entity.entityEnumerations.DeletedStatus;
import com.banking.entity.entityEnumerations.ManagerStatus;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@NonNullApi
public interface ManagerRepository extends JpaRepository<Manager, UUID> {

    Optional<Manager> findById(UUID uuid);

    Optional<Manager> findManagerByFirstNameAndLastName(String firstName, String lastName);

    List<Manager> findManagersByStatus(ManagerStatus status);

    List<Manager> findManagersByDeletedStatus(DeletedStatus deletedStatus);

    List<Manager> findManagersByCreatedAt(Timestamp date);

}
