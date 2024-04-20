package com.test.itau.chavepix.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class PixKeyQueryDTO {
    private UUID id;
    private String keyTYpe;
    private String agencyNumber;
    private String accountNumber;
    private String accountHolderName;
}


