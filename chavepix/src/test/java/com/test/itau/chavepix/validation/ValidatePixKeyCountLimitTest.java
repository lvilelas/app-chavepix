package com.test.itau.chavepix.validation;
import com.test.itau.chavepix.exceptions.InvalidBusinessRule;
import com.test.itau.chavepix.mocks.AccountPixKeysModelMocks;
import com.test.itau.chavepix.model.AccountPixKeysModel;
import com.test.itau.chavepix.model.KeyTypeModel;
import com.test.itau.chavepix.model.PixKeyModel;
import com.test.itau.chavepix.validation.pixkey.ValidatePixKeyCountLimit;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidatePixKeyCountLimitTest extends AccountPixKeysModelMocks {

    @Test
    public void testValidatePixKeyCountLimit() {
        ValidatePixKeyCountLimit validator = new ValidatePixKeyCountLimit();

        AccountPixKeysModel accountPixKeys = getValidAccountCPFPixKeysModelMock();
        accountPixKeys.setPixKeys(Collections.nCopies(20, new PixKeyModel("X", KeyTypeModel.ALEATORIO))); // Filling the list with 20 elements

        assertThrows(InvalidBusinessRule.class, () -> {
            validator.validate(accountPixKeys, getValidCPFPixKeyMock());
        });
    }
}