package com.test.itau.chavepix.dto;

import org.apache.tomcat.util.collections.CaseInsensitiveKeyMap;

import java.util.EnumSet;
import java.util.Map;

public enum KeyTypeDTO {
    CELULAR,
    EMAIL,
    CPF,
    CNPJ,
    ALEATORIO;

    private static final Map<String, KeyTypeDTO> map = new CaseInsensitiveKeyMap<>();

    static {
        EnumSet.allOf(KeyTypeDTO.class).forEach(keyType -> map.put(keyType.name(), keyType));
    }

    public static KeyTypeDTO getByDescription(String description) {
        return map.get(description);
    }
}
