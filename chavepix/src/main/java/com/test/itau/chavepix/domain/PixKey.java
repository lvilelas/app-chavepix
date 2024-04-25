package com.test.itau.chavepix.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class PixKey {

    private UUID id;

    private KeyType keyType;

    private String keyValue;

    private AccountType accountType;

    private PersonType personType;

    private BigInteger agencyNumber;

    private BigInteger accountNumber;

    private String accountHolderName;

    private String accountHolderLastName;

    private LocalDateTime dateTimeCreation;

    private LocalDateTime dateTimeDelete;

}
