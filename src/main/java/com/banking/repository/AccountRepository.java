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

@Repository
@NonNullApi
public interface AccountRepository extends JpaRepository<Account, UUID> {

    Optional<Account> findAccountById(UUID uuid);

    Optional<Account> findAccountByIdAndCurrencyCode(UUID id, CurrencyCode currencyCode);

    List<Account> findAccountsByIdAndName(UUID id, String name);

    List<Account> findAccountsByIdAndStatus(UUID id, AccountStatus status);

    List<Account> findAccountsByIdAndType(UUID id, AccountType type);

    List<Account> findAccountsByIdAndCurrencyCode(UUID id, CurrencyCode currencyCode);

    List<Account> findAccountsByCreatedAt(Timestamp dateCreation);

    List<Account> findAccountsByUpdatedAt(Timestamp dateUpdate);

    List<Account> findAccountsByIdAndDeletedStatus(UUID id, DeletedStatus deletedStatus);
    List<Account> findAccountsByDeletedStatus(DeletedStatus deletedStatus);

    @Query("SELECT account FROM Account account " +
            "WHERE account.iBan = :iBan")
    Optional<Account> findByIBan(@Param("iBan") String iBan);

}
