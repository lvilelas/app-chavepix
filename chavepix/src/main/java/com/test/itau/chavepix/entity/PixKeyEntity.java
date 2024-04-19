package com.test.itau.chavepix.entity;

import com.test.itau.chavepix.dto.PixKeyDTO;
import jakarta.persistence.*;
import lombok.*;

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
    private KeyTypeEntity keyTypeEntity;

    private String keyValue;

    @Enumerated(EnumType.STRING)
    private AccountTypeEntity accountTypeEntity;

    @Enumerated(EnumType.STRING)
    private PersonTypeEntity personTypeEntity;

    private String agencyNumber;

    private String accountNumber;

    private String accountHolderName;

    private String accountHolderLastName;


    public PixKeyEntity(PixKeyDTO pixKeyDTO) {
        this.keyTypeEntity = KeyTypeEntity.valueOf(pixKeyDTO.getKeyTypeDTO().name());
        this.keyValue = pixKeyDTO.getKeyValue();
        this.accountTypeEntity = AccountTypeEntity.valueOf(pixKeyDTO.getAccountTypeDTO().name());
        this.personTypeEntity = PersonTypeEntity.valueOf(pixKeyDTO.getPersonTypeDTO().name());
        this.agencyNumber = pixKeyDTO.getAgencyNumber();
        this.accountNumber = pixKeyDTO.getAccountNumber();
        this.accountHolderName = pixKeyDTO.getAccountHolderName();
        this.accountHolderLastName = pixKeyDTO.getAccountHolderLastName();
    }
}
