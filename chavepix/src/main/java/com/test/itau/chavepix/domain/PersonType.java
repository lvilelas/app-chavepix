package com.test.itau.chavepix.domain;

import org.apache.tomcat.util.collections.CaseInsensitiveKeyMap;

import java.util.EnumSet;
import java.util.Map;

public enum PersonType {
    FISICA,
    JURIDICA;

    private static final Map<String,PersonType> map = new CaseInsensitiveKeyMap<>();

    static{
        EnumSet.allOf(PersonType.class).forEach(personType -> map.put(personType.name(), personType));
    }

    public static PersonType getPersonType(String type){
        return map.get(type);
    }

}
