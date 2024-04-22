package com.test.itau.chavepix.validation;
import com.test.itau.chavepix.exceptions.InvalidBusinessRule;
import com.test.itau.chavepix.validation.pixkey.ValidatePixKeyField;
import com.test.itau.chavepix.validation.query.ValidateDateQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidateDateQueryTest {

    ValidateDateQuery validator;
    @BeforeEach
    void setUp() {
        validator = new ValidateDateQuery();
    }


    @Test
    void testValidate_EmptyMap() {
        Map<String, String> emptyMap = new HashMap<>();
        assertThrows(InvalidBusinessRule.class, () -> validator.validate(emptyMap));
    }

    @Test
    void testValidate_NonEmptyMap() {
        Map<String, String> nonEmptyMap = new HashMap<>();
        nonEmptyMap.put("key", "value");

        validator.validate(nonEmptyMap); // Should not throw any exception
    }
}
