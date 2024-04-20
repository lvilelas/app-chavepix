package com.test.itau.chavepix.mocks;

import com.test.itau.chavepix.dto.AccountTypeDTO;
import com.test.itau.chavepix.dto.KeyTypeDTO;
import com.test.itau.chavepix.dto.PersonTypeDTO;
import com.test.itau.chavepix.dto.PixKeyDTO;

public class PixKeyDTOMocks {

    public PixKeyDTO getValidCPFPixKeyMock(){

        return new PixKeyDTO(null,
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

        return new PixKeyDTO(null,
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

        return new PixKeyDTO(null,
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

        return new PixKeyDTO(null,
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

        return new PixKeyDTO(null,
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

        return new PixKeyDTO(null,
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

        return new PixKeyDTO(null,
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

        return new PixKeyDTO(null,
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

        return new PixKeyDTO(null,
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

        return new PixKeyDTO(null,
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

        return new PixKeyDTO(null,
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

        return new PixKeyDTO(null,
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

        return new PixKeyDTO(null,
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

        return new PixKeyDTO(null,
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

        return new PixKeyDTO(null,
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

        return new PixKeyDTO(null,
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

        return new PixKeyDTO(null,
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

        return new PixKeyDTO(null,
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

        return new PixKeyDTO(null,
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

        return new PixKeyDTO(null,
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

        return new PixKeyDTO(null,
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

        return new PixKeyDTO(null,
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
