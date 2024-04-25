package com.test.itau.chavepix.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "pix_keys")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class PixKeyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name="keyType")
    private KeyTypeEntity keyTypeEntity;

    private String keyValue;

    @Enumerated(EnumType.STRING)
    @Column(name="accountType")
    private AccountTypeEntity accountTypeEntity;

    @Enumerated(EnumType.STRING)
    @Column(name="personType")
    private PersonTypeEntity personTypeEntity;

    private BigInteger agencyNumber;

    private BigInteger accountNumber;

    private String accountHolderName;

    private String accountHolderLastName;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private LocalDateTime dateTimeCreation;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateTimeDelete;

}
