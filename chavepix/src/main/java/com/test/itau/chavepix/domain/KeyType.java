package com.test.itau.chavepix.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.tomcat.util.collections.CaseInsensitiveKeyMap;

import java.util.EnumSet;
import java.util.Map;

@Getter
@AllArgsConstructor
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
