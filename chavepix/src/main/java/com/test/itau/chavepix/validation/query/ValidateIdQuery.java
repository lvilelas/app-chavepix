package com.test.itau.chavepix.validation.query;

import java.util.Map;

public class ValidateIdQuery extends AbstractPixKeyQueryValidationHandler{
    @Override
    protected void validate(Map<String, String> map) {
        if(map.containsKey("id") && map.size()>1){
            throw new RuntimeException("Search by id dosent permit others params");
        }
    }
}
