package com.banking.entity;

import com.banking.entity.entityEnumerations.ClientStatus;
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

import java.util.Date;
import java.util.UUID;

//@Data
//@Entity
//@Table(name = "client")
//@AllArgsConstructor
//@NoArgsConstructor
public class Client {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID", strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "manager_id")//, columnDefinition = "BINARY(16)")
    private UUID managerId;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ClientStatus status;

    @Column(name = "tax_code",length = 20)//, columnDefinition = "VARCHAR(20)")
    private String taxCode;

    @Column(name = "first_name", length = 58)//, columnDefinition = "VARCHAR(50)")
    private String firstName;

    @Column(name = "last_name", length = 50)//, columnDefinition = "VARCHAR(50)")
    private String lastName;

    @Column(name = "email", length = 60)//, columnDefinition = "VARCHAR(60)")
    private String email;

    @Column(name = "address", length = 80)//, columnDefinition = "VARCHAR(80)")
    private String address;

    @Column(name = "phone", length = 20)//, columnDefinition = "VARCHAR(20)")
    private String phone;

    @Column(name = "created_at", nullable = false, updatable = false)//,columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at", nullable = false)//, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updatedAt;

}
