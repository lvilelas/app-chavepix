package com.test.itau.chavepix.validation;

import br.com.fluentvalidator.AbstractValidator;
import com.test.itau.chavepix.dto.PixKeyQueryDTO;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.function.Function;

@Component
public class PixKeySearchValidation extends AbstractValidator<PixKeyQueryDTO> {

    @Override
    public void rules() {
        validateID();
        validateDateTime();
        validateDateCreate();
        validateDateDelete();
        validateNumberOfParameters();
    }

    void validateID() {
        ruleFor(Function.identity())
                .must(pixKeyQueryDTO -> pixKeyQueryDTO.getParameters().isEmpty() && Objects.nonNull(pixKeyQueryDTO.getId()))
                .when(pixKeyQueryDTO -> Objects.nonNull(pixKeyQueryDTO.getId()))
                .withMessage("Search by id dosent work with parameters")
                .withFieldName("id");
    }

    void validateDateCreate() {
        ruleFor(pixKeyQueryDTO -> {
            return pixKeyQueryDTO.getParameters().get("data_inclusao");
        })
                .must(stringStringMap -> {
                    return stringStringMap.matches("(\\d{2})/(\\d{2})/(\\d{4})");
                })
                .when(Objects::nonNull)
                .withMessage("Field data_inclusao didnt match the type YYYY-MM-DD")
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
                .withMessage("Field data_exclusao didnt match the type YYYY-MM-DDTHH:MM:SS")
                .withFieldName("data_exclusao")
                .critical();
    }

    void validateDateTime() {
        ruleFor(PixKeyQueryDTO::getParameters)
                .must(stringStringMap -> {
                    return !(stringStringMap.containsKey("data_inclusao") && stringStringMap.containsKey("data_exclusao"));
                })
                .withMessage("Cannot search both data_criacao and data_delecao")
                .critical();
    }

    void validateNumberOfParameters() {
        ruleFor(PixKeyQueryDTO::getParameters)
                .must(stringStringMap -> stringStringMap.size()<=6)
                .withMessage("Only 6 parameters are allowed")
                .critical();
    }
}

