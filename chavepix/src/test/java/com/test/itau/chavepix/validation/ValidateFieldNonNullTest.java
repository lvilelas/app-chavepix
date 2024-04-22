package com.test.itau.chavepix.validation;
import com.test.itau.chavepix.dto.PixKeyDTO;
import com.test.itau.chavepix.exceptions.InvalidFieldException;
import com.test.itau.chavepix.mocks.PixKeyDTOMocks;
import com.test.itau.chavepix.validation.pixkey.ValidateFieldNonNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.NotReadablePropertyException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidateFieldNonNullTest extends PixKeyDTOMocks {

    @Test
    void testValidate_InvalidField() {
        // Given
        PixKeyDTO pixKeyDTO = getInvalidAccountHolderNameNullMock();
        ValidateFieldNonNull validator = new ValidateFieldNonNull("accountHolderName");

        // When/Then
        assertThrows(InvalidFieldException.class, () -> validator.validate(pixKeyDTO));
    }

    @Test
    void testValidate_ValidField() {
        // Given
        PixKeyDTO pixKeyDTO = getValidCPFPixKeyMock();

        ValidateFieldNonNull validator = new ValidateFieldNonNull("accountHolderName");

        // When/Then
        validator.validate(pixKeyDTO); // No exception should be thrown
    }
}
