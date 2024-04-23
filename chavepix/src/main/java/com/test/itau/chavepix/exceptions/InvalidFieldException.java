package com.test.itau.chavepix.exceptions;


import br.com.fluentvalidator.context.ValidationResult;
import br.com.fluentvalidator.exception.ValidationException;


public class InvalidFieldException extends ValidationException {

    protected InvalidFieldException(ValidationResult validationResult) {
        super(validationResult);
    }
}
