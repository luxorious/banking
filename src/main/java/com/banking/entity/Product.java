package com.banking.entity;

import com.banking.entity.entityEnumerations.CurrencyCode;
import com.banking.entity.entityEnumerations.DeletedStatus;
import com.banking.entity.entityEnumerations.ProductStatus;
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

import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", unique = true, nullable = false)
    private UUID id;

    @Column(name = "manager_id")
    private UUID managerId;

    @Column(name = "product_name")
    private String name;

    @Column(name = "product_status")
    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @Column(name = "currency_code")
    @Enumerated(EnumType.STRING)
    private CurrencyCode currencyCode;

    @Column(name = "interest_rate")
    private Double interestRate; //numeric(6,4) | interest rate of product |

    @Column(name = "product_limit")
    private Double limit;//numeric(15,2) | limit of credit a product ( 0 - no limit, 0 < - limit which can be used) |

    @Column(name = "deleted_status")
    @Enumerated(EnumType.STRING)
    private DeletedStatus deletedStatus = DeletedStatus.ACTIVE;

    @Column(name = "created_at", updatable = false, nullable = false, columnDefinition = "DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp createdAt;

    @Column(name = "updated_at", updatable = false, nullable = false, columnDefinition = "DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp updatedAt;
}
