package com.test.itau.chavepix.validation;
import com.test.itau.chavepix.exceptions.InvalidBusinessRule;
import com.test.itau.chavepix.mocks.AccountPixKeysModelMocks;
import com.test.itau.chavepix.validation.pixkey.ValidatePixKeyType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class     ValidatePixKeyTypeTest extends AccountPixKeysModelMocks {

    @Test
    public void testValidatePixKeyTypeWithInvalidPersonType(){
        ValidatePixKeyType validator = new ValidatePixKeyType();

        assertDoesNotThrow(() -> {
            validator.validate(getValidAccountCPFPixKeysModelMock(), getValidCPFPixKeyMock());
        });
    }

    @Test
    public void testValidatePixKeyTypeCNPJWithInvalidPersonType(){
        ValidatePixKeyType validator = new ValidatePixKeyType();

        assertDoesNotThrow(() -> {
            validator.validate(getValidAccountCNPJPixKeysModelMock(), getValidCNPJPixKeyMock());
        });
    }

    @Test
    public void testValidatePixKeyTypeWithInvalidPersonTypeError(){
        ValidatePixKeyType validator = new ValidatePixKeyType();

        assertThrows(RuntimeException.class,() -> {
            validator.validate(getValidAccountCPFPixKeysModelMock(), getInvalidCPFPersonTypeErrorMock());
        });
    }

    @Test
    public void testValidateCNPJPixKeyTypeWithInvalidPersonTypeError(){
        ValidatePixKeyType validator = new ValidatePixKeyType();

        assertThrows(InvalidBusinessRule.class,() -> {
            validator.validate(getValidAccountCNPJPixKeysModelMock(), getInvalidCNPJPersonTypeErrorMock());
        });
    }
}
