package com.banking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.Text;

import java.util.UUID;

@Entity
@Table(name = "documents")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Document {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name= "client_id")
    private UUID clientId;

    @OneToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id", updatable = false, insertable = false)
    private Client client;

    @Lob
    @Column(name = "passport")
    private byte[] passport;

    @Lob
    @Column(name = "registration")
    private byte[] registration;
}
