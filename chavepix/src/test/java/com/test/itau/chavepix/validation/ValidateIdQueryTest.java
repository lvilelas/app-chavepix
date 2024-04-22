package com.test.itau.chavepix.validation;

import com.test.itau.chavepix.exceptions.InvalidBusinessRule;
import com.test.itau.chavepix.validation.query.ValidateIdQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidateIdQueryTest {

    ValidateIdQuery validator;

    @BeforeEach
    void setUp() {
        validator = new ValidateIdQuery();
    }

    @Test
    void testValidate_IdWithOtherParams() {
        ValidateIdQuery validator = new ValidateIdQuery();

        Map<String, String> params = new HashMap<>();
        params.put("id", "123");
        params.put("otherParam", "value");

        assertThrows(InvalidBusinessRule.class, () -> validator.validate(params));
    }

    @Test
    void testValidate_IdWithoutOtherParams() {
        ValidateIdQuery validator = new ValidateIdQuery();

        Map<String, String> params = new HashMap<>();
        params.put("id", "123");

        validator.validate(params); // Should not throw any exception
    }

    @Test
    void testValidate_WithoutId() {
        ValidateIdQuery validator = new ValidateIdQuery();

        Map<String, String> params = new HashMap<>();
        params.put("otherParam", "value");

        validator.validate(params); // Should not throw any exception
    }
}