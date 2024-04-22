package com.test.itau.chavepix.validation.query;

import com.test.itau.chavepix.exceptions.InvalidBusinessRule;

import java.util.Map;

public class ValidateIdQuery extends AbstractPixKeyQueryValidationHandler{
    @Override
    public void validate(Map<String, String> map) {
        if(map.containsKey("id") && map.size()>1){
            throw new InvalidBusinessRule("Search by id dosent permit others params");
        }
    }
}
