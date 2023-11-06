package com.banking.repository;

import com.banking.entity.Account;
import com.banking.entity.entityenumerations.AccountStatus;
import com.banking.entity.entityenumerations.AccountType;
import com.banking.entity.entityenumerations.CurrencyCode;
import com.banking.entity.entityenumerations.DeletedStatus;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for managing Account entities.
 * This interface extends JpaRepository to provide basic CRUD operations for Account entities.
 */
@Repository
@NonNullApi
public interface AccountRepository extends JpaRepository<Account, UUID> {

    /**
     * Find an Account by its ID.
     *
     * @param uuid The ID of the Account to find.
     * @return An optional containing the found Account, or an empty optional if not found.
     */
    Optional<Account> findAccountById(UUID uuid);

    /**
     * Find an Account by its ID and CurrencyCode.
     *
     * @param id           The ID of the Account to find.
     * @param currencyCode The CurrencyCode of the Account to find.
     * @return An optional containing the found Account, or an empty optional if not found.
     */
    Optional<Account> findAccountByIdAndCurrencyCode(UUID id, CurrencyCode currencyCode);

    /**
     * Find Accounts by their ID and Name.
     *
     * @param id   The ID of the Accounts to find.
     * @param name The Name of the Accounts to find.
     * @return A list of Accounts that match the provided ID and Name.
     */
    List<Account> findAccountsByIdAndName(UUID id, String name);

    /**
     * Find Accounts by their ID and AccountStatus.
     *
     * @param id     The ID of the Accounts to find.
     * @param status The AccountStatus of the Accounts to find.
     * @return A list of Accounts that match the provided ID and AccountStatus.
     */
    List<Account> findAccountsByIdAndStatus(UUID id, AccountStatus status);

    /**
     * Find Accounts by their ID and AccountType.
     *
     * @param id   The ID of the Accounts to find.
     * @param type The AccountType of the Accounts to find.
     * @return A list of Accounts that match the provided ID and AccountType.
     */
    List<Account> findAccountsByIdAndType(UUID id, AccountType type);

    /**
     * Find Accounts by their ID and CurrencyCode.
     *
     * @param id           The ID of the Accounts to find.
     * @param currencyCode The CurrencyCode of the Accounts to find.
     * @return A list of Accounts that match the provided ID and CurrencyCode.
     */
    List<Account> findAccountsByIdAndCurrencyCode(UUID id, CurrencyCode currencyCode);

    /**
     * Find Accounts created at the specified date.
     *
     * @param dateCreation The date of creation of the Accounts to find.
     * @return A list of Accounts that were created on the specified date.
     */
    List<Account> findAccountsByCreatedAt(Timestamp dateCreation);

    /**
     * Find Accounts updated at the specified date.
     *
     * @param dateUpdate The date of update of the Accounts to find.
     * @return A list of Accounts that were updated on the specified date.
     */
    List<Account> findAccountsByUpdatedAt(Timestamp dateUpdate);

    /**
     * Find Accounts by their ID and DeletedStatus.
     *
     * @param id            The ID of the Accounts to find.
     * @param deletedStatus The DeletedStatus of the Accounts to find.
     * @return A list of Accounts that match the provided ID and DeletedStatus.
     */
    List<Account> findAccountsByIdAndDeletedStatus(UUID id, DeletedStatus deletedStatus);

    /**
     * Find Accounts by their DeletedStatus.
     *
     * @param deletedStatus The DeletedStatus of the Accounts to find.
     * @return A list of Accounts that match the provided DeletedStatus.
     */
    List<Account> findAccountsByDeletedStatus(DeletedStatus deletedStatus);

    /**
     * Custom query to find an Account by its IBAN.
     *
     * @param iBan The IBAN of the Account to find.
     * @return An optional containing the found Account, or an empty optional if not found.
     */
    @Query("SELECT account FROM Account account " +
            "WHERE account.iBan = :iBan")
    Optional<Account> findByIBan(@Param("iBan") String iBan);

}
