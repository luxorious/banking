package com.banking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "bank")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Bank {
    @Id
    private UUID id;

    @Column(name = "iban")
    private String iban;

    @Column(name = "balance")
    private BigDecimal balance;

}
