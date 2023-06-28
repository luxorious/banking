package com.banking.entity;

import com.banking.entity.entityenumerations.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class Account {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "client_id")
    private UUID clientId;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Client client;

    @Column(name = "account_name", length = 100)
    private String name;

    @Column(name = "account_type")
    @Enumerated(EnumType.STRING)
    private AccountType type;

    @Column(name = "account_status")
    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @Column(name = "account_balance", precision = 15, scale = 2)
    private BigDecimal balance;

    @Column(name = "currency_code")
    @Enumerated(EnumType.STRING)
    private CurrencyCode currencyCode;

    @Column(name = "deleted_status")
    @Enumerated(EnumType.STRING)
    private DeletedStatus deletedStatus;

    @Column(name = "i_ban", nullable = false, updatable = false, length = 29)
    private String iBan;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Timestamp updatedAt;

    //for test in postman
    public Account(String name, BigDecimal balance) {
        this.name = name;
        this.balance = balance;
    }
}
