package com.test.itau.chavepix.validation;

import br.com.fluentvalidator.AbstractValidator;
import com.test.itau.chavepix.domain.KeyType;
import com.test.itau.chavepix.dto.PixKeyQueryDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.Objects;
import java.util.function.Function;

@Component
public class PixKeySearchValidation extends AbstractValidator<PixKeyQueryDTO> {

    @Override
    public void rules() {
        validateID();
        validateKeyType();
        validateDateTime();
        validateDateCreate();
        validateDateDelete();
        validateAgencyNumber();
        validateAccountNumber();
        validateNumberOfParameters();
    }

    private void validateAgencyNumber() {
        ruleFor(pixKeyQueryDTO -> {
            return pixKeyQueryDTO.getParameters().get("numero_agencia");
        })
                .must(s -> {
                    if(StringUtils.isNoneBlank(s)){
                        BigInteger sNumber = new BigInteger(s);
                        return (sNumber.compareTo(BigInteger.valueOf(9999)) <= 0 &&
                                sNumber.compareTo(BigInteger.valueOf(1)) >= 0);
                    }
                    return false;
                    }
                )
                .when(Objects::nonNull)
                .withMessage("Agency Number Invalid")
                .withFieldName("numero_agencia")
                .critical();
    }

    private void validateAccountNumber() {
        ruleFor(pixKeyQueryDTO -> {
            return pixKeyQueryDTO.getParameters().get("numero_conta");
        })
                .must(s -> {
                            if(StringUtils.isNoneBlank(s)){
                                BigInteger sNumber = new BigInteger(s);
                                return (sNumber.compareTo(BigInteger.valueOf(99999999)) <= 0 &&
                                        sNumber.compareTo(BigInteger.valueOf(1)) >= 0);
                            }
                            return false;
                        }
                )
                .when(Objects::nonNull)
                .withMessage("Account number invalid")
                .withFieldName("numero_conta")
                .critical();
    }

    void validateID() {
        ruleFor(Function.identity())
                .must(pixKeyQueryDTO -> pixKeyQueryDTO.getParameters().isEmpty() && Objects.nonNull(pixKeyQueryDTO.getId()))
                .when(pixKeyQueryDTO -> Objects.nonNull(pixKeyQueryDTO.getId()))
                .withMessage("Search by id dosent work with parameters")
                .withFieldName("id")
                .critical();
    }

    void validateKeyType(){
        ruleFor(pixKeyQueryDTO -> {
            return pixKeyQueryDTO.getParameters().get("tipo_chave");
        })
                .must(s -> {
                    return StringUtils.isNoneBlank(s) && Objects.nonNull(KeyType.getByDescription(s));
                })
                .when(Objects::nonNull)
                .withMessage("Field tipo_chave need to be CPF or CNPJ")
                .withFieldName("tipo_chave")
                .critical();
    }

    void validateDateCreate() {
        ruleFor(pixKeyQueryDTO -> {
            return pixKeyQueryDTO.getParameters().get("data_inclusao");
        })
                .must(stringStringMap -> {
                    return stringStringMap.matches("(\\d{2})/(\\d{2})/(\\d{4})");
                })
                .when(Objects::nonNull)
                .withMessage("Field data_inclusao didnt match the type DD/MM/YYYY")
                .withFieldName("data_inclusao")
                .critical();
    }

    void validateDateDelete() {
        ruleFor(pixKeyQueryDTO -> {
            return pixKeyQueryDTO.getParameters().get("data_exclusao");
        })
                .must(stringStringMap -> {
                    return stringStringMap.matches("(\\d{2})/(\\d{2})/(\\d{4})");
                })
                .when(Objects::nonNull)
                .withMessage("Field data_exclusao didnt match the type DD/MM/YYYY")
                .withFieldName("data_exclusao")
                .critical();
    }

    void validateDateTime() {
        ruleFor(PixKeyQueryDTO::getParameters)
                .must(stringStringMap -> {
                    return !(stringStringMap.containsKey("data_inclusao") && stringStringMap.containsKey("data_exclusao"));
                })
                .withMessage("Cannot search both data_inclusao and data_exclusao")
                .withFieldName("data_inclusao | data_exclusao")
                .critical();
    }

    void validateNumberOfParameters() {
        ruleFor(PixKeyQueryDTO::getParameters)
                .must(stringStringMap -> stringStringMap.size()<=6)
                .withMessage("Only 6 parameters are allowed")
                .critical();
    }
}

