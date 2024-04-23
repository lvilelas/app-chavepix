package com.test.itau.chavepix.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class PixKeyDTO {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("tipo_chave")
    private String keyType;

    @JsonProperty("valor_chave")
    private String keyValue;

    @JsonProperty("tipo_conta")
    private String accountType;

    @JsonProperty("tipo_pessoa")
    private String personType;

    @JsonProperty("numero_agencia")
    private BigInteger agencyNumber;

    @JsonProperty("numero_conta")
    private BigInteger accountNumber;

    @JsonProperty("nome_correntista")
    private String accountHolderName;

    @JsonProperty("sobrenome_correntista")
    private String accountHolderLastName;

}
