package com.test.itau.chavepix.exceptions;


import br.com.fluentvalidator.context.ValidationResult;
import br.com.fluentvalidator.exception.ValidationException;
import com.test.itau.chavepix.dto.ErrorDTO;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;


@Getter
public class PixKeyException extends ValidationException {

    private final List<ErrorDTO> errors;

    public PixKeyException(ValidationResult validationResult) {
        super(validationResult);
        this.errors = validationResult.getErrors().stream().map(error -> new ErrorDTO(error.getField(), error.getMessage())).collect(Collectors.toList());
    }


}
