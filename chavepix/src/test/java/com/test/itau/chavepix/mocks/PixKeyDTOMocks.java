package com.test.itau.chavepix.mocks;

import com.test.itau.chavepix.dto.AccountTypeDTO;
import com.test.itau.chavepix.dto.KeyTypeDTO;
import com.test.itau.chavepix.dto.PersonTypeDTO;
import com.test.itau.chavepix.dto.PixKeyDTO;

import java.util.UUID;

public class PixKeyDTOMocks {

    public PixKeyDTO getValidCPFPixKeyMock(){

        return new PixKeyDTO(
                UUID.randomUUID(),
                KeyTypeDTO.CPF,
                "37819393831",
                AccountTypeDTO.CORRENTE,
                PersonTypeDTO.FISICA,
                "1234",
                "12345678",
                "Luiz",
                "Silva"
        );

    }

    public PixKeyDTO getValidCNPJPixKeyMock(){

        return new PixKeyDTO(
                UUID.randomUUID(),
                KeyTypeDTO.CNPJ,
                "48135933000112",
                AccountTypeDTO.CORRENTE,
                PersonTypeDTO.JURIDICA,
                "1234",
                "12345678",
                "Luiz",
                "Silva"
        );

    }

    public PixKeyDTO getInvalidPixKeyValueCPF(){

        return new PixKeyDTO(
                UUID.randomUUID(),
                KeyTypeDTO.CPF,
                "378193938311",
                AccountTypeDTO.CORRENTE,
                PersonTypeDTO.FISICA,
                "1234",
                "12345678",
                "Luiz",
                "Silva"
        );
    }

    public PixKeyDTO getInvalidPixKeyValueCNPJ(){

        return new PixKeyDTO(
                UUID.randomUUID(),
                KeyTypeDTO.CNPJ,
                "378193938311",
                AccountTypeDTO.CORRENTE,
                PersonTypeDTO.JURIDICA,
                "1234",
                "12345678",
                "Luiz",
                "Silva"
        );
    }

    public PixKeyDTO getInvalidPixKeyValueMobile(){

        return new PixKeyDTO(
                UUID.randomUUID(),
                KeyTypeDTO.CELULAR,
                "+1119384",
                AccountTypeDTO.CORRENTE,
                PersonTypeDTO.JURIDICA,
                "1234",
                "12345678",
                "Luiz",
                "Silva"
        );
    }

    public PixKeyDTO getInvalidPixKeyValueEmail(){

        return new PixKeyDTO(
                UUID.randomUUID(),
                KeyTypeDTO.EMAIL,
                "+1119384",
                AccountTypeDTO.CORRENTE,
                PersonTypeDTO.JURIDICA,
                "1234",
                "12345678",
                "Luiz",
                "Silva"
        );
    }

    public PixKeyDTO getInvalidPixKeyValueRandom(){

        return new PixKeyDTO(
                UUID.randomUUID(),
                KeyTypeDTO.ALEATORIO,
                "123213214521515215214124215216216276347587659758758",
                AccountTypeDTO.CORRENTE,
                PersonTypeDTO.JURIDICA,
                "1234",
                "12345678",
                "Luiz",
                "Silva"
        );
    }

    public PixKeyDTO getInvalidPixKeyValueEmpty(){

        return new PixKeyDTO(
                UUID.randomUUID(),
                KeyTypeDTO.ALEATORIO,
                "",
                AccountTypeDTO.CORRENTE,
                PersonTypeDTO.JURIDICA,
                "1234",
                "12345678",
                "Luiz",
                "Silva"
        );
    }

    public PixKeyDTO getInvalidPixKeyValueNull(){

        return new PixKeyDTO(
                UUID.randomUUID(),
                KeyTypeDTO.ALEATORIO,
                "",
                AccountTypeDTO.CORRENTE,
                PersonTypeDTO.JURIDICA,
                "1234",
                "12345678",
                "Luiz",
                "Silva"
        );
    }


    public PixKeyDTO getInvalidPixKeyType(){

        return new PixKeyDTO(
                UUID.randomUUID(),
                null,
                "378193938311",
                AccountTypeDTO.CORRENTE,
                PersonTypeDTO.FISICA,
                "1234",
                "12345678",
                "Luiz",
                "Silva"
        );
    }

    public PixKeyDTO getInvalidAccountTypeMock(){

        return new PixKeyDTO(
                UUID.randomUUID(),
                KeyTypeDTO.CPF,
                "37819393831",
                null,
                PersonTypeDTO.FISICA,
                "1234",
                "12345678",
                "Luiz",
                "Silva"
        );
    }

    public PixKeyDTO getInvalidPersonTypeMock(){

        return new PixKeyDTO(
                UUID.randomUUID(),
                KeyTypeDTO.CPF,
                "37819393831",
                AccountTypeDTO.CORRENTE,
                null,
                "1234",
                "12345678",
                "Luiz",
                "Silva"
        );
    }

    public PixKeyDTO getInvalidAgencyNumberMock(){

        return new PixKeyDTO(
                UUID.randomUUID(),
                KeyTypeDTO.CPF,
                "37819393831",
                AccountTypeDTO.CORRENTE,
                PersonTypeDTO.FISICA,
                "12345",
                "12345678",
                "Luiz",
                "Silva"
        );
    }

    public PixKeyDTO getInvalidAgencyNumberEmptyMock(){

        return new PixKeyDTO(
                UUID.randomUUID(),
                KeyTypeDTO.CPF,
                "37819393831",
                AccountTypeDTO.CORRENTE,
                PersonTypeDTO.FISICA,
                "",
                "12345678",
                "Luiz",
                "Silva"
        );
    }

    public PixKeyDTO getInvalidAgencyNumberNullMock(){

        return new PixKeyDTO(
                UUID.randomUUID(),
                KeyTypeDTO.CPF,
                "37819393831",
                AccountTypeDTO.CORRENTE,
                PersonTypeDTO.FISICA,
                null,
                "12345678",
                "Luiz",
                "Silva"
        );
    }

    public PixKeyDTO getInvalidAccountNumberMock(){

        return new PixKeyDTO(
                UUID.randomUUID(),
                KeyTypeDTO.CPF,
                "37819393831",
                AccountTypeDTO.CORRENTE,
                PersonTypeDTO.FISICA,
                "1234",
                "123456789",
                "Luiz",
                "Silva"
        );
    }


    public PixKeyDTO getInvalidAccountNumberNullMock(){

        return new PixKeyDTO(
                UUID.randomUUID(),
                KeyTypeDTO.CPF,
                "37819393831",
                AccountTypeDTO.CORRENTE,
                PersonTypeDTO.FISICA,
                "1234",
                null,
                "Luiz",
                "Silva"
        );
    }

    public PixKeyDTO getInvalidAccountNumberEmptyMock(){

        return new PixKeyDTO(
                UUID.randomUUID(),
                KeyTypeDTO.CPF,
                "37819393831",
                AccountTypeDTO.CORRENTE,
                PersonTypeDTO.FISICA,
                "1234",
                "",
                "Luiz",
                "Silva"
        );
    }


    public PixKeyDTO getInvalidAccountHolderNameEmptyMock(){

        return new PixKeyDTO(
                UUID.randomUUID(),
                KeyTypeDTO.CPF,
                "37819393831",
                AccountTypeDTO.CORRENTE,
                PersonTypeDTO.FISICA,
                "1234",
                "12345678",
                "",
                "Silva"
        );
    }

    public PixKeyDTO getInvalidAccountHolderNameNullMock(){

        return new PixKeyDTO(
                UUID.randomUUID(),
                KeyTypeDTO.CPF,
                "37819393831",
                AccountTypeDTO.CORRENTE,
                PersonTypeDTO.FISICA,
                "1234",
                "12345678",
                null,
                "Silva"
        );
    }

    public PixKeyDTO getInvalidCPFPersonTypeErrorMock(){

        return new PixKeyDTO(
                UUID.randomUUID(),
                KeyTypeDTO.CPF,
                "37819393831",
                AccountTypeDTO.CORRENTE,
                PersonTypeDTO.JURIDICA,
                "1234",
                "12345678",
                null,
                "Silva"
        );
    }

    public PixKeyDTO getInvalidCNPJPersonTypeErrorMock(){

        return new PixKeyDTO(
                UUID.randomUUID(),
                KeyTypeDTO.CNPJ,
                "37819393831",
                AccountTypeDTO.CORRENTE,
                PersonTypeDTO.FISICA,
                "1234",
                "12345678",
                null,
                "Silva"
        );
    }
}
