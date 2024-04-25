package com.test.itau.chavepix.persistence.entity;

import com.test.itau.chavepix.domain.KeyType;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.collections.CaseInsensitiveKeyMap;

import java.util.EnumSet;
import java.util.Map;

@AllArgsConstructor
public enum KeyTypeEntity {
    CELULAR,
    EMAIL,
    CPF,
    CNPJ,
    ALEATORIO;

    private static final Map<String, KeyTypeEntity> map = new CaseInsensitiveKeyMap<>();

    static {
        EnumSet.allOf(KeyTypeEntity.class).forEach(keyType -> map.put(keyType.name(), keyType));
    }

    public static KeyTypeEntity getByDescription(String description) {
        return map.get(description);
    }
}
