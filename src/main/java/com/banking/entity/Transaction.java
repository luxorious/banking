package com.banking.entity;

import com.banking.entity.entityenumerations.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "debit_account_id")
    private UUID debitAccountId;

    @ManyToOne
    @JoinColumn(name = "debit_account_id", referencedColumnName = "id", updatable = false, insertable = false)
    private Account debitAccount;

    @Column(name = "credit_account_id")
    private UUID creditAccountId;

    @ManyToOne
    @JoinColumn(name = "credit_account_id", referencedColumnName = "id", updatable = false, insertable = false)
    private Account creditAccount;

    @Column(name = "transaction_type", length = 60)
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @Column(name = "transaction_amount", precision = 20, scale = 2)
    private BigDecimal amount;

    @Column(name = "transaction_description", length = 254)
    private String description;

    @Column(name = "iban", length = 29)
    private String iBan;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Timestamp createdAt;
}