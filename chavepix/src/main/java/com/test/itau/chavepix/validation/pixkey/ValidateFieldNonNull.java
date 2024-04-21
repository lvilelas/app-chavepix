package com.test.itau.chavepix.validation.pixkey;

import com.test.itau.chavepix.dto.PixKeyDTO;
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
                throw new NotReadablePropertyException(PixKeyDTO.class, field + " key is not valid");
            }
        } catch (Exception e) {
            throw new NotReadablePropertyException(PixKeyDTO.class, field + " key is not valid");
        }
    }
}
