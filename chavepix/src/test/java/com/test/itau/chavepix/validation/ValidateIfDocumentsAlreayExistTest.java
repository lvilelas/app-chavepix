package com.test.itau.chavepix.validation;
import com.test.itau.chavepix.dto.PixKeyDTO;
import com.test.itau.chavepix.mocks.AccountPixKeysModelMocks;
import com.test.itau.chavepix.model.AccountPixKeysModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidateIfDocumentsAlreayExistTest extends AccountPixKeysModelMocks {

    @Test
    public void testValidateIfDocumentsAlreayExist() {
        ValidateIfDocumentsAlreayExist validator = new ValidateIfDocumentsAlreayExist();

        assertThrows(RuntimeException.class, () -> {
            validator.validate(getValidAccountCPFPixKeysModelMock(), getValidCPFPixKeyMock());
        });
    }
}