package com.test.itau.chavepix.validation;

import com.test.itau.chavepix.exceptions.InvalidBusinessRule;
import com.test.itau.chavepix.validation.query.ValidateNullQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidateNullQueryTest {

    ValidateNullQuery validator;

    @BeforeEach
    void setUp() {
        validator = new ValidateNullQuery();
    }

    @Test
    void testValidate_BothParamsPresent() {
        ValidateNullQuery validator = new ValidateNullQuery();

        Map<String, String> params = new HashMap<>();
        params.put("data_inclusao", "2022-04-28");
        params.put("data_exclusao", "2022-04-30");

        assertThrows(InvalidBusinessRule.class, () -> validator.validate(params));
    }

    @Test
    void testValidate_DataInclusaoPresent() {
        ValidateNullQuery validator = new ValidateNullQuery();

        Map<String, String> params = new HashMap<>();
        params.put("data_inclusao", "2022-04-28");

        validator.validate(params); // Should not throw any exception
    }

    @Test
    void testValidate_DataExclusaoPresent() {
        ValidateNullQuery validator = new ValidateNullQuery();

        Map<String, String> params = new HashMap<>();
        params.put("data_exclusao", "2022-04-30");

        validator.validate(params); // Should not throw any exception
    }

    @Test
    void testValidate_NoParams() {
        ValidateNullQuery validator = new ValidateNullQuery();

        Map<String, String> params = new HashMap<>();

        validator.validate(params); // Should not throw any exception
    }
}
