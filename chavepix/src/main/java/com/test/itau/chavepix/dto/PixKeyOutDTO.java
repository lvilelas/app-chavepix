package com.test.itau.chavepix.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.itau.chavepix.persistence.entity.PixKeyEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class PixKeyOutDTO {

    private UUID id;

    @JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
    @JsonProperty("tipo_chave")
    private KeyTypeDTO keyTypeDTO;

    @JsonProperty("valor_chave")
    private String keyValue;

    @JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
    @JsonProperty("tipo_conta")
    private AccountTypeDTO accountTypeDTO;

    @JsonProperty("tipo_pessoa")
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
    private PersonTypeDTO personTypeDTO;

    @JsonProperty("numero_agencia")
    private String agencyNumber;

    @JsonProperty("numero_conta")
    private String accountNumber;

    @JsonProperty("nome_correntista")
    private String accountHolderName;

    @JsonProperty("sobrenome_correntista")
    private String accountHolderLastName;

    @JsonProperty("data_inclusao")
    private String dateTimeCreation;


    public PixKeyOutDTO(PixKeyEntity pixKeyEntity) {
        this.id = pixKeyEntity.getId();
        this.keyTypeDTO = KeyTypeDTO.valueOf(pixKeyEntity.getKeyTypeEntity().name());
        this.keyValue = pixKeyEntity.getKeyValue();
        this.accountTypeDTO = AccountTypeDTO.valueOf(pixKeyEntity.getAccountTypeEntity().name());
        this.personTypeDTO = PersonTypeDTO.valueOf(pixKeyEntity.getPersonTypeEntity().name());
        this.agencyNumber = pixKeyEntity.getAgencyNumber();
        this.accountNumber = pixKeyEntity.getAccountNumber();
        this.accountHolderName = pixKeyEntity.getAccountHolderName();
        this.accountHolderLastName = pixKeyEntity.getAccountHolderLastName();
        this.dateTimeCreation = pixKeyEntity.getDateTimeCreation().toString();
    }
}
