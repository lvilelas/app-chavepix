package com.test.itau.chavepix.validation;

import com.test.itau.chavepix.exceptions.InvalidFieldException;
import com.test.itau.chavepix.mocks.PixKeyDTOMocks;
import com.test.itau.chavepix.validation.pixkey.ValidatePixKeyField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.NotReadablePropertyException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidatePixKeyFieldTest extends PixKeyDTOMocks {

    private ValidatePixKeyField validator;

    @BeforeEach
    void setUp() {
        validator = new ValidatePixKeyField();
    }

    @Test
    void testValidationWithValidPixKeyCNPJValueDTO() {
        validator.validate(getValidCNPJPixKeyMock());
    }
    @Test
    void testValidationWithValidPixKeyValueDTO() {
        validator.validate(getValidCPFPixKeyMock());
    }

    @Test
    public void testValidationWithInvalidPixKeyValueDTO() throws Exception {
        assertThrows(InvalidFieldException.class, () -> validator.validate(getInvalidPixKeyValueCPF()));
    }

    @Test
    public void testValidationWithInvalidPixKeyValueCNPJDTO() throws Exception {
        assertThrows(InvalidFieldException.class, () -> validator.validate(getInvalidPixKeyValueCNPJ()));
    }

    @Test
    public void testValidationWithInvalidPixKeyValueEmailDTO() throws Exception {
        assertThrows(InvalidFieldException.class, () -> validator.validate(getInvalidPixKeyValueEmail()));
    }

    @Test
    public void testValidationWithInvalidPixKeyValueMobileDTO() throws Exception {
        assertThrows(InvalidFieldException.class, () -> validator.validate(getInvalidPixKeyValueMobile()));
    }

    @Test
    public void testValidationWithInvalidPixKeyValueRandomDTO() throws Exception {
        assertThrows(InvalidFieldException.class, () -> validator.validate(getInvalidPixKeyValueRandom()));
    }

    @Test
    public void testValidationWithInvalidPixKeyValueEmptyDTO() throws Exception {
        assertThrows(InvalidFieldException.class, () -> validator.validate(getInvalidPixKeyValueEmpty()));
    }

    @Test
    public void testValidationWithInvalidPixKeyValueNullDTO() throws Exception {
        assertThrows(InvalidFieldException.class, () -> validator.validate(getInvalidPixKeyValueNull()));
    }
}
