package com.test.itau.chavepix.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class PixKeyQueryDTO {
    private UUID id;
    private String keyType;
    private String agencyNumber;
    private String accountNumber;
    private String accountHolderName;
}


