package com.test.itau.chavepix.validation;
import com.test.itau.chavepix.exceptions.InvalidBusinessRule;
import com.test.itau.chavepix.mocks.AccountPixKeysModelMocks;
import com.test.itau.chavepix.validation.pixkey.ValidateIfDocumentsAlreayExist;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidateIfDocumentsAlreayExistTest extends AccountPixKeysModelMocks {

    @Test
    public void testValidateIfDocumentsAlreayExist() {
        ValidateIfDocumentsAlreayExist validator = new ValidateIfDocumentsAlreayExist();

        assertThrows(InvalidBusinessRule.class, () -> {
            validator.validate(getValidAccountCPFPixKeysModelMock(), getValidCPFPixKeyMock());
        });
    }
}