package com.test.itau.chavepix.validation;
import com.test.itau.chavepix.dto.PixKeyDTO;
import com.test.itau.chavepix.mocks.AccountPixKeysModelMocks;
import com.test.itau.chavepix.model.AccountPixKeysModel;
import com.test.itau.chavepix.model.KeyTypeModel;
import com.test.itau.chavepix.model.PixKeyModel;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidatePixKeyCountLimitTest extends AccountPixKeysModelMocks {

    @Test
    public void testValidatePixKeyCountLimit() {
        ValidatePixKeyCountLimit validator = new ValidatePixKeyCountLimit();

        AccountPixKeysModel accountPixKeys = getValidAccountCPFPixKeysModelMock();
        accountPixKeys.setPixKeys(Collections.nCopies(20, new PixKeyModel("X", KeyTypeModel.ALEATORIO))); // Filling the list with 20 elements

        assertThrows(RuntimeException.class, () -> {
            validator.validate(accountPixKeys, getValidCPFPixKeyMock());
        });
    }
}