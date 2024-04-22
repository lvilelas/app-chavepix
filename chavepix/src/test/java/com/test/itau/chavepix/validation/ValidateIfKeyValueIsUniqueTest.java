package com.test.itau.chavepix.validation;

import com.test.itau.chavepix.exceptions.InvalidBusinessRule;
import com.test.itau.chavepix.mocks.AccountPixKeysModelMocks;
import com.test.itau.chavepix.persistence.repository.PixKeyRepository;
import com.test.itau.chavepix.validation.pixkey.ValidateIfKeyValueIsUnique;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ValidateIfKeyValueIsUniqueTest extends AccountPixKeysModelMocks {

    @Test
    public void testValidateIfKeyValueIsUnique() {
        PixKeyRepository pixKeyRepository = mock(PixKeyRepository.class);
        when(pixKeyRepository.existsByKeyValueAndDateTimeDeleteIsNull(anyString())).thenReturn(true); // Assuming the key value already exists

        ValidateIfKeyValueIsUnique validator = new ValidateIfKeyValueIsUnique(pixKeyRepository);

        assertThrows(InvalidBusinessRule.class, () -> {
            validator.validate(getValidAccountCPFPixKeysModelMock(), getValidCPFPixKeyMock());
        });
    }
}

