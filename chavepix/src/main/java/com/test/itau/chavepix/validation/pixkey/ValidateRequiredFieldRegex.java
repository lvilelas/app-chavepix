package com.test.itau.chavepix.validation.pixkey;

import com.test.itau.chavepix.dto.PixKeyDTO;
import org.springframework.beans.NotReadablePropertyException;
import org.springframework.validation.Errors;

public class ValidateRequiredFieldRegex extends AbstractPixKeyRequestValidationHandler{


    public final String field;
    public final String regex;

    public ValidateRequiredFieldRegex(String field, String regex) {
        this.field = field;
        this.regex = regex;
    }

    @Override
    protected void validate(PixKeyDTO pixKey) {
        try {
            if(!hasValidRequiredFieldRegex(pixKey)){
                throw new NotReadablePropertyException(PixKeyDTO.class,field + " key is not valid");
            }
        } catch (Exception e) {
            throw new NotReadablePropertyException(PixKeyDTO.class,field + " key is not valid");
        }
    }

    private boolean hasValidRequiredFieldRegex(PixKeyDTO pixKey) throws NoSuchFieldException, IllegalAccessException {
        String requiredField = (String) reflectionAttributeMapper.getFieldValue(pixKey,field);
        if(requiredField==null || requiredField.isEmpty()){
            return false;
        }
        return requiredField.matches(regex);
    }
}
