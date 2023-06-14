package com.banking.repository;

import com.banking.entity.Account;
import com.banking.entity.entityEnumerations.AccountStatus;
import com.banking.entity.entityEnumerations.AccountType;
import com.banking.entity.entityEnumerations.CurrencyCode;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@NonNullApi
public interface AccountRepository extends JpaRepository<Account, UUID> {

    Optional<Account> findAccountById(UUID uuid);

    List<Account> findAccountsByName(String name);

    List<Account> findAccountsByStatus(AccountStatus status);

    List<Account> findAccountsByType(AccountType type);

    List<Account> findAccountsByCurrencyCode(CurrencyCode currencyCode);

    List<Account> findAccountsByCreatedAt(Timestamp dateCreation);

    List<Account> findAccountsByUpdatedAt(Timestamp dateUpdate);

    boolean updateAccountByClientId(UUID clientId);
    void updateAccountById(UUID id);
    List<Account> updateAccountsByStatus(AccountStatus status);

    void deleteById(UUID id);

    void deleteAccountsByName(String name);
    List<Account> deleteAccountsByStatus(AccountStatus status);
}
