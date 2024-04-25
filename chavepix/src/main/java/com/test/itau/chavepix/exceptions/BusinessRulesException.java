package com.test.itau.chavepix.exceptions;

import com.test.itau.chavepix.dto.ErrorDTO;
import lombok.Getter;

@Getter
public class BusinessRulesException extends RuntimeException {

    private final ErrorDTO errorDTO;

    public BusinessRulesException(String fieldName, String error) {
        errorDTO = new ErrorDTO(fieldName, error);
    }
}
