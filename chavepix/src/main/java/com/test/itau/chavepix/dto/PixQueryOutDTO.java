package com.test.itau.chavepix.dto;

import com.fasterxml.jackson.annotation.*;
import com.test.itau.chavepix.persistence.entity.PixKeyEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class PixQueryOutDTO {

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
    private LocalDateTime dateTimeCreation;

    @JsonProperty("data_exclusao")
    private LocalDateTime dateTimeDelete;

    public PixQueryOutDTO(PixKeyEntity pixKeyDTO) {
        this.id = pixKeyDTO.getId();
        this.keyTypeDTO = KeyTypeDTO.valueOf(pixKeyDTO.getKeyTypeEntity().name());
        this.keyValue = pixKeyDTO.getKeyValue();
        this.accountTypeDTO = AccountTypeDTO.valueOf(pixKeyDTO.getAccountTypeEntity().name());
        this.personTypeDTO = PersonTypeDTO.valueOf(pixKeyDTO.getPersonTypeEntity().name());
        this.agencyNumber = pixKeyDTO.getAgencyNumber();
        this.accountNumber = pixKeyDTO.getAccountNumber();
        this.accountHolderName = pixKeyDTO.getAccountHolderName();
        this.accountHolderLastName = pixKeyDTO.getAccountHolderLastName();
        this.dateTimeCreation = pixKeyDTO.getDateTimeCreation();
        this.dateTimeDelete = pixKeyDTO.getDateTimeDelete();
    }
}
