package com.test.itau.chavepix.validation.query;

import java.util.Map;

public class ValidateNullQuery extends AbstractPixKeyQueryValidationHandler{
    @Override
    protected void validate(Map<String, String> map) {
        if(map.containsKey("data_inclusao") && map.containsKey("data_exclusao")){
            throw new RuntimeException("Cannot search both parametes 'data_inclusao' and 'data_exclusao'");
        }
    }
}
