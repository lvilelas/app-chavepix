package com.test.itau.chavepix.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class PixKeyQueryDTO {
    private String keyType;
    private BigDecimal agencyNumber;
    private BigDecimal accountNumber;
    private String accountHolderName;

}


