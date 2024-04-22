package com.test.itau.chavepix.validation.query;

import com.test.itau.chavepix.exceptions.InvalidBusinessRule;

import java.util.Map;

public class ValidateEmptyQuery extends AbstractPixKeyQueryValidationHandler{
    @Override
    public void validate(Map<String, String> map) {
        if(map.isEmpty()){
            throw new InvalidBusinessRule("None parameter sent");
        }
    }
}
