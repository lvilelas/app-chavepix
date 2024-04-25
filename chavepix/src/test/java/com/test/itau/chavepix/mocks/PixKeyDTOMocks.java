package com.test.itau.chavepix.mocks;

import com.test.itau.chavepix.domain.*;
import com.test.itau.chavepix.dto.*;
import com.test.itau.chavepix.persistence.entity.AccountTypeEntity;
import com.test.itau.chavepix.persistence.entity.KeyTypeEntity;
import com.test.itau.chavepix.persistence.entity.PersonTypeEntity;
import com.test.itau.chavepix.persistence.entity.PixKeyEntity;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.UUID;

public class PixKeyDTOMocks {



    public PixKeyDeleteOutDTO getPixKeyDeleteOutDTO() {
        PixKeyDeleteOutDTO pixKeyDTO = new PixKeyDeleteOutDTO();
        pixKeyDTO.setId(UUID.randomUUID());
        pixKeyDTO.setKeyValue("123");
        pixKeyDTO.setAccountTypeDTO(AccountTypeDTO.CORRENTE);
        pixKeyDTO.setKeyTypeDTO(KeyTypeDTO.CPF);
        pixKeyDTO.setPersonTypeDTO(PersonTypeDTO.FISICA);
        pixKeyDTO.setDateTimeCreation(LocalDateTime.now().toString());
        pixKeyDTO.setDateTimeDelete(LocalDateTime.now().toString());
        pixKeyDTO.setAgencyNumber("11");
        pixKeyDTO.setAccountNumber("123");
        pixKeyDTO.setAgencyNumber("123");
        return pixKeyDTO;

    }
    public PixKeyEntity getPixKeyEntity(){
        PixKeyEntity pixKeyEntity = new PixKeyEntity();
        pixKeyEntity.setId(UUID.randomUUID());
        pixKeyEntity.setAccountTypeEntity(AccountTypeEntity.POUPANCA);
        pixKeyEntity.setAgencyNumber(BigInteger.valueOf(2));
        pixKeyEntity.setAccountNumber(BigInteger.valueOf(3));
        pixKeyEntity.setAccountHolderName("Teste");
        pixKeyEntity.setDateTimeCreation(LocalDateTime.now());
        pixKeyEntity.setKeyTypeEntity(KeyTypeEntity.CPF);
        pixKeyEntity.setPersonTypeEntity(PersonTypeEntity.FISICA);
        return pixKeyEntity;
    }


    public PixKeyQuery getPixKeyQuery(){
        PixKeyQuery pixKeyQuery = new PixKeyQuery();
        pixKeyQuery.setAgencyNumber("1234");
        pixKeyQuery.setAccountNumber("1234");
        pixKeyQuery.setKeyType("CPF");
        return pixKeyQuery;
    }

    public PixKeyUpdateDTO getPixUpdated(){
        return new PixKeyUpdateDTO(UUID.randomUUID(),
                "JURIDICA",
                BigInteger.valueOf(1234),
                BigInteger.valueOf(12345678),
                "teste",
                "teste");
    }


    public PixKey getValidPixKey(){
        return new PixKey(UUID.randomUUID(),
                KeyType.CPF,
                "37819393831",
                AccountType.CORRENTE,
                PersonType.FISICA,
                BigInteger.valueOf(1234),
                BigInteger.valueOf(12345678),
                "Luiz",
                "Silva",
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    public PixKey getValidPixKeyEmail(){
        return new PixKey(UUID.randomUUID(),
                KeyType.EMAIL,
                "378193@93831",
                AccountType.CORRENTE,
                PersonType.FISICA,
                BigInteger.valueOf(1234),
                BigInteger.valueOf(12345678),
                "Luiz",
                "Silva",
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }


    public PixKeyDTO getInvalidAccountNumberMock(){

        return new PixKeyDTO(

                KeyTypeDTO.CPF.name(),
                "37819393831",
                AccountTypeDTO.CORRENTE.name(),
                PersonTypeDTO.FISICA.name(),
                BigInteger.valueOf(1234),
                BigInteger.valueOf(123456789),
                "Luiz",
                "Silva"
        );
    }

    public PixKeyDTO getValidCPFPixKeyMock(){

        return new PixKeyDTO(
                KeyTypeDTO.CPF.name(),
                "37819393831",
                AccountTypeDTO.CORRENTE.name(),
                PersonTypeDTO.FISICA.name(),
                BigInteger.valueOf(1234),
                BigInteger.valueOf(12345678),
                "Luiz",
                "Silva"
        );

    }
}
