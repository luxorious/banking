package com.banking.repository;

import com.banking.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository interface for managing Bank entities.
 * This interface extends JpaRepository to provide basic CRUD operations for Bank entities.
 */
@Repository
public interface BankRepository extends JpaRepository<Bank, UUID> {
}
