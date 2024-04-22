package com.test.itau.chavepix.validation.query;

import com.test.itau.chavepix.exceptions.InvalidBusinessRule;

import java.util.Map;

public class ValidateDateQuery extends AbstractPixKeyQueryValidationHandler{
    @Override
    public void validate(Map<String, String> map) {
        if(map.containsKey("data_inclusao") && map.containsKey("data_exclusao")){
            throw new InvalidBusinessRule("Cannot search both parametes 'data_inclusao' and 'data_exclusao'");
        }
    }
}
