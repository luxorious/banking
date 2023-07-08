package com.banking.entity;

import com.banking.entity.entityenumerations.CreditStatus;
import com.banking.entity.entityenumerations.CreditType;
import com.banking.entity.entityenumerations.CurrencyCode;
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
@Table(name = "credit")
public class Credit {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "credit_sum")
    private BigDecimal sumOfCredit;

    @Column(name = "interest")
    private Double interest;

    @Column(name = "client_id")
    private UUID clientId;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id", updatable = false, insertable = false)
    private Client client;

    @Column(name = "number_of_month")
    private Integer numberOfMonth;

    @Column(name = "paymentPerMonth")
    private BigDecimal paymentPerMonth;

    @Column(name = "credit_type")
    @Enumerated(EnumType.STRING)
    private CreditType creditType;

    @Column(name = "curency_code")
    @Enumerated(EnumType.STRING)
    private CurrencyCode currencyCode;

    @Column(name = "credit_status")
    @Enumerated(EnumType.STRING)
    private CreditStatus creditStatus;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Timestamp updatedAt;
}
