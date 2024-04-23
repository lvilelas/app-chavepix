package com.test.itau.chavepix.mocks;

import com.test.itau.chavepix.dto.AccountTypeDTO;
import com.test.itau.chavepix.dto.KeyTypeDTO;
import com.test.itau.chavepix.dto.PersonTypeDTO;
import com.test.itau.chavepix.dto.PixKeyDTO;

import java.math.BigInteger;
import java.util.UUID;

public class PixKeyDTOMocks {

//    public PixKeyDTO getValidCPFPixKeyMock(){
//
//        return new PixKeyDTO(
//                UUID.randomUUID(),
//                KeyTypeDTO.CPF.name(),
//                "37819393831",
//                AccountTypeDTO.CORRENTE.name(),
//                PersonTypeDTO.FISICA.name(),
//                BigInteger.valueOf(1234),
//                BigInteger.valueOf(12345678),
//                "Luiz",
//                "Silva"
//        );
//
//    }
//
//    public PixKeyDTO getValidCNPJPixKeyMock(){
//
//        return new PixKeyDTO(
//                UUID.randomUUID(),
//                KeyTypeDTO.CNPJ.name(),
//                "48135933000112",
//                AccountTypeDTO.CORRENTE.name(),
//                PersonTypeDTO.JURIDICA.name(),
//                BigInteger.valueOf(1234),
//                BigInteger.valueOf(12345678),
//                "Luiz",
//                "Silva"
//        );
//
//    }
//
//    public PixKeyDTO getInvalidPixKeyValueCPF(){
//
//        return new PixKeyDTO(
//                UUID.randomUUID(),
//                KeyTypeDTO.CPF.name(),
//                "378193938311",
//                AccountTypeDTO.CORRENTE.name(),
//                PersonTypeDTO.FISICA.name(),
//                BigInteger.valueOf(1234),
//                BigInteger.valueOf(12345678),
//                "Luiz",
//                "Silva"
//        );
//    }
//
//    public PixKeyDTO getInvalidPixKeyValueCNPJ(){
//
//        return new PixKeyDTO(
//                UUID.randomUUID(),
//                KeyTypeDTO.CNPJ.name(),
//                "378193938311",
//                AccountTypeDTO.CORRENTE.name(),
//                PersonTypeDTO.JURIDICA.name(),
//                BigInteger.valueOf(1234),
//                BigInteger.valueOf(12345678),
//                "Luiz",
//                "Silva"
//        );
//    }
//
//    public PixKeyDTO getInvalidPixKeyValueMobile(){
//
//        return new PixKeyDTO(
//                UUID.randomUUID(),
//                KeyTypeDTO.CELULAR.name(),
//                "+1119384",
//                AccountTypeDTO.CORRENTE.name(),
//                PersonTypeDTO.JURIDICA.name(),
//                BigInteger.valueOf(1234),
//                BigInteger.valueOf(12345678),
//                "Luiz",
//                "Silva"
//        );
//    }
//
//    public PixKeyDTO getInvalidPixKeyValueEmail(){
//
//        return new PixKeyDTO(
//                UUID.randomUUID(),
//                KeyTypeDTO.EMAIL.name(),
//                "+1119384",
//                AccountTypeDTO.CORRENTE.name(),
//                PersonTypeDTO.JURIDICA.name(),
//                BigInteger.valueOf(1234),
//                BigInteger.valueOf(12345678),
//                "Luiz",
//                "Silva"
//        );
//    }
//
//    public PixKeyDTO getInvalidPixKeyValueRandom(){
//
//        return new PixKeyDTO(
//                UUID.randomUUID(),
//                KeyTypeDTO.ALEATORIO.name(),
//                "123213214521515215214124215216216276347587659758758",
//                AccountTypeDTO.CORRENTE.name(),
//                PersonTypeDTO.JURIDICA.name(),
//                BigInteger.valueOf(1234),
//                BigInteger.valueOf(12345678),
//                "Luiz",
//                "Silva"
//        );
//    }
//
//    public PixKeyDTO getInvalidPixKeyValueEmpty(){
//
//        return new PixKeyDTO(
//                UUID.randomUUID(),
//                KeyTypeDTO.ALEATORIO.name(),
//                "",
//                AccountTypeDTO.CORRENTE.name(),
//                PersonTypeDTO.JURIDICA.name(),
//                BigInteger.valueOf(1234),
//                BigInteger.valueOf(12345678),
//                "Luiz",
//                "Silva"
//        );
//    }
//
//    public PixKeyDTO getInvalidPixKeyValueNull(){
//
//        return new PixKeyDTO(
//                UUID.randomUUID(),
//                KeyTypeDTO.ALEATORIO.name(),
//                "",
//                AccountTypeDTO.CORRENTE.name(),
//                PersonTypeDTO.JURIDICA.name(),
//                BigInteger.valueOf(1234),
//                BigInteger.valueOf(12345678),
//                "Luiz",
//                "Silva"
//        );
//    }
//
//
//    public PixKeyDTO getInvalidPixKeyType(){
//
//        return new PixKeyDTO(
//                UUID.randomUUID(),
//                null,
//                "378193938311",
//                AccountTypeDTO.CORRENTE.name(),
//                PersonTypeDTO.FISICA.name(),
//                BigInteger.valueOf(1234),
//                BigInteger.valueOf(12345678),
//                "Luiz",
//                "Silva"
//        );
//    }
//
//    public PixKeyDTO getInvalidAccountTypeMock(){
//
//        return new PixKeyDTO(
//                UUID.randomUUID(),
//                KeyTypeDTO.CPF.name(),
//                "37819393831",
//                null,
//                PersonTypeDTO.FISICA.name(),
//                BigInteger.valueOf(1234),
//                BigInteger.valueOf(12345678),
//                "Luiz",
//                "Silva"
//        );
//    }
//
//    public PixKeyDTO getInvalidPersonTypeMock(){
//
//        return new PixKeyDTO(
//                UUID.randomUUID(),
//                KeyTypeDTO.CPF.name(),
//                "37819393831",
//                AccountTypeDTO.CORRENTE.name(),
//                null,
//                BigInteger.valueOf(1234),
//                BigInteger.valueOf(12345678),
//                "Luiz",
//                "Silva"
//        );
//    }
//
//    public PixKeyDTO getInvalidAgencyNumberMock(){
//
//        return new PixKeyDTO(
//                UUID.randomUUID(),
//                KeyTypeDTO.CPF.name(),
//                "37819393831",
//                AccountTypeDTO.CORRENTE.name(),
//                PersonTypeDTO.FISICA.name(),
//                BigInteger.valueOf(12345),
//                BigInteger.valueOf(12345678),
//                "Luiz",
//                "Silva"
//        );
//    }
//
//    public PixKeyDTO getInvalidAgencyNumberEmptyMock(){
//
//        return new PixKeyDTO(
//                UUID.randomUUID(),
//                KeyTypeDTO.CPF.name(),
//                "37819393831",
//                AccountTypeDTO.CORRENTE.name(),
//                PersonTypeDTO.FISICA.name(),
//                BigInteger.valueOf(0),
//                BigInteger.valueOf(12345678),
//                "Luiz",
//                "Silva"
//        );
//    }
//
//    public PixKeyDTO getInvalidAgencyNumberNullMock(){
//
//        return new PixKeyDTO(
//                UUID.randomUUID(),
//                KeyTypeDTO.CPF.name(),
//                "37819393831",
//                AccountTypeDTO.CORRENTE.name(),
//                PersonTypeDTO.FISICA.name(),
//                null,
//                BigInteger.valueOf(12345678),
//                "Luiz",
//                "Silva"
//        );
//    }
//
//    public PixKeyDTO getInvalidAccountNumberMock(){
//
//        return new PixKeyDTO(
//                UUID.randomUUID(),
//                KeyTypeDTO.CPF.name(),
//                "37819393831",
//                AccountTypeDTO.CORRENTE.name(),
//                PersonTypeDTO.FISICA.name(),
//                BigInteger.valueOf(1234),
//                BigInteger.valueOf(123456789),
//                "Luiz",
//                "Silva"
//        );
//    }
//
//
//    public PixKeyDTO getInvalidAccountNumberNullMock(){
//
//        return new PixKeyDTO(
//                UUID.randomUUID(),
//                KeyTypeDTO.CPF.name(),
//                "37819393831",
//                AccountTypeDTO.CORRENTE.name(),
//                PersonTypeDTO.FISICA.name(),
//                BigInteger.valueOf(1234),
//                null,
//                "Luiz",
//                "Silva"
//        );
//    }
//
//    public PixKeyDTO getInvalidAccountNumberEmptyMock(){
//
//        return new PixKeyDTO(
//                UUID.randomUUID(),
//                KeyTypeDTO.CPF.name(),
//                "37819393831",
//                AccountTypeDTO.CORRENTE.name(),
//                PersonTypeDTO.FISICA.name(),
//                BigInteger.valueOf(1234),
//                BigInteger.valueOf(0),
//                "Luiz",
//                "Silva"
//        );
//    }
//
//
//    public PixKeyDTO getInvalidAccountHolderNameEmptyMock(){
//
//        return new PixKeyDTO(
//                UUID.randomUUID(),
//                KeyTypeDTO.CPF.name(),
//                "37819393831",
//                AccountTypeDTO.CORRENTE.name(),
//                PersonTypeDTO.FISICA.name(),
//                BigInteger.valueOf(1234),
//                BigInteger.valueOf(12345678),
//                "",
//                "Silva"
//        );
//    }
//
//    public PixKeyDTO getInvalidAccountHolderNameNullMock(){
//
//        return new PixKeyDTO(
//                UUID.randomUUID(),
//                KeyTypeDTO.CPF.name(),
//                "37819393831",
//                AccountTypeDTO.CORRENTE.name(),
//                PersonTypeDTO.FISICA.name(),
//                BigInteger.valueOf(1234),
//                BigInteger.valueOf(12345678),
//                null,
//                "Silva"
//        );
//    }
//
//    public PixKeyDTO getInvalidCPFPersonTypeErrorMock(){
//
//        return new PixKeyDTO(
//                UUID.randomUUID(),
//                KeyTypeDTO.CPF.name(),
//                "37819393831",
//                AccountTypeDTO.CORRENTE.name(),
//                PersonTypeDTO.JURIDICA.name(),
//                BigInteger.valueOf(1234),
//                BigInteger.valueOf(12345678),
//                null,
//                "Silva"
//        );
//    }
//
//    public PixKeyDTO getInvalidCNPJPersonTypeErrorMock(){
//
//        return new PixKeyDTO(
//                UUID.randomUUID(),
//                KeyTypeDTO.CNPJ.name(),
//                "37819393831",
//                AccountTypeDTO.CORRENTE.name(),
//                PersonTypeDTO.FISICA.name(),
//                BigInteger.valueOf(1234),
//                BigInteger.valueOf(12345678),
//                null,
//                "Silva"
//        );
//    }
}
