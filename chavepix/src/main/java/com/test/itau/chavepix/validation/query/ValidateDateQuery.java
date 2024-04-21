package com.test.itau.chavepix.validation.query;

import java.util.Map;

public class ValidateDateQuery extends AbstractPixKeyQueryValidationHandler{
    @Override
    public void validate(Map<String, String> map) {
        if(map.isEmpty()){
            throw new RuntimeException("None parameter send");
        }
    }
}
