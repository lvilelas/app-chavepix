package com.test.itau.chavepix.validation.pixkey;

import com.test.itau.chavepix.dto.PixKeyDTO;
import org.springframework.beans.NotReadablePropertyException;

public class ValidateFieldRegex extends AbstractPixKeyRequestValidationHandler{


    public final String field;
    public final String regex;

    public ValidateFieldRegex(String field, String regex) {
        this.field = field;
        this.regex = regex;

    }

    @Override
    public void validate(PixKeyDTO pixKey) {
        try {
            String requiredField = (String) reflectionAttributeMapper.getFieldValue(pixKey,field);
            if(requiredField!=null && !requiredField.matches(regex)){
                throw new NotReadablePropertyException(PixKeyDTO.class,field + " key is not valid");
            }

        } catch (Exception e) {
            throw new NotReadablePropertyException(PixKeyDTO.class,field + " key is not valid");
        }
    }
}
