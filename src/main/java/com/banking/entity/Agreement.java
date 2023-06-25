package com.banking.entity;

import com.banking.entity.entityenumerations.AgreementStatus;
import com.banking.entity.entityenumerations.DeletedStatus;
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
@Table(name = "agreement")
public class Agreement {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "account_id")
    private UUID accountId;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Account account;

    @Column(name = "product_id")
    private UUID productId;

    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Product product;

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

    @Column(name = "created_at", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Timestamp updatedAt;
}
