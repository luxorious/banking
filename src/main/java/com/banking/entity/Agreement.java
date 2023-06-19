package com.banking.entity;

import com.banking.entity.entityEnumerations.AgreementStatus;
import com.banking.entity.entityEnumerations.DeletedStatus;
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

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "agreement")
public class Agreement {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", unique = true, nullable = false)
    private UUID id;

    @Column(name = "account_id")
    private UUID accountId;

    @Column(name = "product_id")
    private UUID productId;

    @Column(name = "interest_rate", precision = 20, scale = 2)
    private BigDecimal interestRate;

    @Column(name = "agreement_status")
    @Enumerated(EnumType.STRING)
    private AgreementStatus status;

    @Column(name = "agreement_sum", precision = 20, scale = 2)
    private BigDecimal sum;

    @Column(name = "deleted_status")
    @Enumerated(EnumType.STRING)
    private DeletedStatus deletedStatus = DeletedStatus.ACTIVE;

    @Column(name = "created_at", updatable = false, nullable = false, columnDefinition = "DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp updatedAt;
}
