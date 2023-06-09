package com.banking.entity;

import com.banking.entity.entityenumerations.ClientStatus;
import com.banking.entity.entityenumerations.DeletedStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@Entity
@Table(name = "client")
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "manager_id")
    private UUID managerId;

    @ManyToOne
    @JoinColumn(name = "manager_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Manager manager;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ClientStatus status;

    @Column(name = "tax_code",length = 20)
    private String taxCode;

    @Column(name = "first_name", length = 58)
    private String firstName;

    @Column(name = "last_name", length = 50)
    private String lastName;

    @Column(name = "email", length = 60)
    private String email;

    @Column(name = "address", length = 80)
    private String address;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "deleted_status")
    @Enumerated(EnumType.STRING)
    private DeletedStatus deletedStatus = DeletedStatus.ACTIVE;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Timestamp updatedAt;

}
