package com.test.itau.chavepix.validation;
import com.test.itau.chavepix.dto.PixKeyDTO;
import com.test.itau.chavepix.exceptions.InvalidFieldException;
import com.test.itau.chavepix.mocks.PixKeyDTOMocks;
import com.test.itau.chavepix.validation.pixkey.ValidateFieldRegex;
import org.junit.jupiter.api.Test;
import org.springframework.beans.NotReadablePropertyException;

import static org.junit.jupiter.api.Assertions.*;

public class ValidateFieldNonNullRegexTest extends PixKeyDTOMocks {

    @Test
    void testValidate_InvalidField() {
        // Given
        PixKeyDTO pixKeyDTO = getInvalidAgencyNumberMock();
        ValidateFieldRegex validator = new ValidateFieldRegex("agencyNumber", "[0-9]{1,4}");

        // When/Then
        assertThrows(InvalidFieldException.class, () -> validator.validate(pixKeyDTO));
    }

    @Test
    void testValidate_ValidField() {
        // Given
        PixKeyDTO pixKeyDTO = getValidCPFPixKeyMock();
        ValidateFieldRegex validator = new ValidateFieldRegex("agencyNumber", "[0-9]{1,4}");

        // When/Then
        validator.validate(pixKeyDTO); // No exception should be thrown
    }
}
