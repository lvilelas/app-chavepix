package com.test.itau.chavepix.domain;

import org.apache.tomcat.util.collections.CaseInsensitiveKeyMap;

import java.util.EnumSet;
import java.util.Map;

public enum KeyType {
    CELULAR,
    EMAIL,
    CPF,
    CNPJ,
    ALEATORIO;

    private static final Map<String, KeyType> map = new CaseInsensitiveKeyMap<>();

    static {
        EnumSet.allOf(KeyType.class).forEach(keyType -> map.put(keyType.name(), keyType));
    }

    public static KeyType getByDescription(String description) {
        return map.get(description);
    }
}
