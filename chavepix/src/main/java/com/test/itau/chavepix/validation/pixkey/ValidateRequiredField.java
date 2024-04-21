package com.test.itau.chavepix.validation.pixkey;

import com.test.itau.chavepix.dto.PixKeyDTO;
import org.springframework.beans.NotReadablePropertyException;
import org.springframework.validation.Errors;

public class ValidateRequiredField extends AbstractPixKeyRequestValidationHandler{

    public final String field;

    public ValidateRequiredField(String field) {
        this.field = field;
    }


    @Override
    protected void validate(PixKeyDTO pixKey) {
        try {
            if(!hasValidRequiredField(pixKey)){
                throw new NotReadablePropertyException(PixKeyDTO.class,field + " key is not valid");
            }
        } catch (Exception e) {
            throw new NotReadablePropertyException(PixKeyDTO.class,field + " key is not valid");
        }
    }

    private boolean hasValidRequiredField(PixKeyDTO pixKey) throws NoSuchFieldException, IllegalAccessException {
        Object requiredField = reflectionAttributeMapper.getFieldValue(pixKey,field);

        return requiredField!=null;
    }
}
