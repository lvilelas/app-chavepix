package com.test.itau.chavepix.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.test.itau.chavepix.persistence.entity.AccountTypeEntity;
import com.test.itau.chavepix.persistence.entity.KeyTypeEntity;
import com.test.itau.chavepix.persistence.entity.PersonTypeEntity;
import com.test.itau.chavepix.persistence.entity.PixKeyEntity;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@AllArgsConstructor
public class PixKeyDTO {

    private String id;

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

    public PixKeyDTO(PixKeyEntity pixKeyDTO) {
        this.keyTypeDTO = KeyTypeDTO.valueOf(pixKeyDTO.getKeyTypeEntity().name());
        this.keyValue = pixKeyDTO.getKeyValue();
        this.accountTypeDTO = AccountTypeDTO.valueOf(pixKeyDTO.getAccountTypeEntity().name());
        this.personTypeDTO = PersonTypeDTO.valueOf(pixKeyDTO.getPersonTypeEntity().name());
        this.agencyNumber = pixKeyDTO.getAgencyNumber();
        this.accountNumber = pixKeyDTO.getAccountNumber();
        this.accountHolderName = pixKeyDTO.getAccountHolderName();
        this.accountHolderLastName = pixKeyDTO.getAccountHolderLastName();
    }
}
