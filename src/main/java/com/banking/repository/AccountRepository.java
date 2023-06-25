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

    List<Account> findAccountsByName(String name);

    List<Account> findAccountsByStatus(AccountStatus status);

    List<Account> findAccountsByType(AccountType type);

    List<Account> findAccountsByCurrencyCode(CurrencyCode currencyCode);

    List<Account> findAccountsByCreatedAt(Timestamp dateCreation);

    List<Account> findAccountsByUpdatedAt(Timestamp dateUpdate);

    List<Account> findAccountsByDeletedStatus(DeletedStatus deletedStatus);

    ///чому не працює?!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//    !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//    Optional<Account> findAccountByIBan(String iBan);

    //а цей метод ідельно спрацьовує!!!!!!!!! в чому проблема?!
    Optional<Account> findAccountByBanBan(String iBan);
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    @Query("SELECT account FROM Account account " +
            "WHERE account.iBan = :iBan")
    Optional<Account> findByIBan(@Param("iBan") String iBan);

}
