package com.test.itau.chavepix.validation;

import com.test.itau.chavepix.dto.PersonTypeDTO;
import com.test.itau.chavepix.dto.PixKeyDTO;
import com.test.itau.chavepix.mocks.AccountPixKeysModelMocks;
import com.test.itau.chavepix.model.AccountPixKeysModel;
import com.test.itau.chavepix.model.PersonTypeModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidatePersonTypeTest extends AccountPixKeysModelMocks {

    @Test
    public void testValidatePersonTypeWithInvalidCPFPersonType(){
        ValidatePersonType validator = new ValidatePersonType();

        assertThrows(RuntimeException.class, () -> {
            validator.validate(getValidAccountCPFPixKeysModelMock(), getValidCNPJPixKeyMock());
        });
    }

    @Test
    public void testValidatePersonTypeWithInvalidCNPJPersonType(){
        ValidatePersonType validator = new ValidatePersonType();

        assertThrows(RuntimeException.class, () -> {
            validator.validate(getValidAccountCNPJPixKeysModelMock(), getValidCPFPixKeyMock());
        });
    }
}