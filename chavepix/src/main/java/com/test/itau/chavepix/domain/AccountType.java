package com.test.itau.chavepix.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.tomcat.util.collections.CaseInsensitiveKeyMap;

import java.util.EnumSet;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum AccountType {
    CORRENTE,
    POUPANCA;

    private static final Map<String,AccountType> map = new CaseInsensitiveKeyMap<>();

    static {
        EnumSet.allOf(AccountType.class).forEach(accountType -> map.put(accountType.name(), accountType));
    }
    public static AccountType getByDescription(String description) {
        return map.get(description);
    }
}
