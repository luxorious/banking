package com.banking.entity;

import com.banking.entity.entityEnumerations.TransactionType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", unique = true, nullable = false)
    private UUID id;

    @Column(name = "debit_account_id")
    private UUID debitAccountId;

    @Column(name = "credit_account_id")
    private UUID creditAccountId;

    @Column(name = "transaction_type", length = 60)
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @Column(name = "transaction_amount") //numeric(12,2)
    private Double amount;

    @Column(name = "transaction_description", length = 255)
    private String description;

    @Column(name = "created_at", columnDefinition = "DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp createdAt;
}