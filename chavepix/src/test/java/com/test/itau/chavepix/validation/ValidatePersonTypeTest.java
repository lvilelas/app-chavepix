package com.test.itau.chavepix.validation;

import com.test.itau.chavepix.exceptions.InvalidBusinessRule;
import com.test.itau.chavepix.mocks.AccountPixKeysModelMocks;
import com.test.itau.chavepix.validation.pixkey.ValidatePersonType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidatePersonTypeTest extends AccountPixKeysModelMocks {

    @Test
    public void testValidatePersonTypeWithInvalidCPFPersonType(){
        ValidatePersonType validator = new ValidatePersonType();

        assertThrows(InvalidBusinessRule.class, () -> {
            validator.validate(getValidAccountCPFPixKeysModelMock(), getValidCNPJPixKeyMock());
        });
    }

    @Test
    public void testValidatePersonTypeWithInvalidCNPJPersonType(){
        ValidatePersonType validator = new ValidatePersonType();

        assertThrows(InvalidBusinessRule.class, () -> {
            validator.validate(getValidAccountCNPJPixKeysModelMock(), getValidCPFPixKeyMock());
        });
    }
}