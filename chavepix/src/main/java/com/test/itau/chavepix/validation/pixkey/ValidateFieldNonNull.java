package com.test.itau.chavepix.validation.pixkey;

import com.test.itau.chavepix.dto.PixKeyDTO;
import com.test.itau.chavepix.exceptions.InvalidFieldException;
import org.springframework.beans.NotReadablePropertyException;

public class ValidateFieldNonNull extends AbstractPixKeyRequestValidationHandler{

    public final String field;

    public ValidateFieldNonNull(String field) {
        this.field = field;
    }


    @Override
    public void validate(PixKeyDTO pixKey) {
        try {
            Object requiredField = getFieldValue(pixKey, field);
            if (requiredField == null) {
                throw new InvalidFieldException(field);
            }
        } catch (Exception e) {
            throw new InvalidFieldException(field);
        }
    }
}
