package com.test.itau.chavepix.validation;

import com.test.itau.chavepix.dto.PixKeyDTO;
import com.test.itau.chavepix.mocks.PixKeyDTOMocks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import static org.junit.jupiter.api.Assertions.*;

public class PixKeyRequestValidatorTest extends PixKeyDTOMocks {

    private PixKeyRequestValidator validator;
    private PixKeyDTO validPixKeyDTO;
    private Errors errors;

    @BeforeEach
    public void setUp() {
        validator = new PixKeyRequestValidator();
        errors = new BeanPropertyBindingResult(validPixKeyDTO, "pixKeyDTO");
    }

    @Test
    public void testValidationWithValidPixKeyDTO() throws Exception{
        validator.validate(getValidCPFPixKeyMock(), errors);
        assertFalse(errors.hasErrors());
    }

    @Test
    public void testValidationWithInvalidPixKeyValueDTO() throws Exception {
        // Create an invalid PixKeyDTO
        // setting account number to null to make it invalid
        assertThrows(Exception.class, () -> {
            validator.validate(getInvalidPixKeyValueCPF(), errors);
        });
    }

    @Test
    public void testValidationWithInvalidPixKeyValueCNPJDTO() throws Exception {
        // Create an invalid PixKeyDTO
        // setting account number to null to make it invalid
        assertThrows(Exception.class, () -> {
            validator.validate(getInvalidPixKeyValueCNPJ(), errors);
        });
    }

    @Test
    public void testValidationWithInvalidPixKeyValueEmailDTO() throws Exception {
        // Create an invalid PixKeyDTO
        // setting account number to null to make it invalid
        assertThrows(Exception.class, () -> {
            validator.validate(getInvalidPixKeyValueEmail(), errors);
        });
    }

    @Test
    public void testValidationWithInvalidPixKeyValueMobileDTO() throws Exception {
        // Create an invalid PixKeyDTO
        // setting account number to null to make it invalid
        assertThrows(Exception.class, () -> {
            validator.validate(getInvalidPixKeyValueMobile(), errors);
        });
    }

    @Test
    public void testValidationWithInvalidPixKeyValueRandomDTO() throws Exception {
        // Create an invalid PixKeyDTO
        // setting account number to null to make it invalid
        assertThrows(Exception.class, () -> {
            validator.validate(getInvalidPixKeyValueRandom(), errors);
        });
    }

    @Test
    public void testValidationWithInvalidPixKeyValueEmptyDTO() throws Exception {
        // Create an invalid PixKeyDTO
        // setting account number to null to make it invalid
        assertThrows(Exception.class, () -> {
            validator.validate(getInvalidPixKeyValueEmpty(), errors);
        });
    }

    @Test
    public void testValidationWithInvalidPixKeyValueNullDTO() throws Exception {
        // Create an invalid PixKeyDTO
        // setting account number to null to make it invalid
        assertThrows(Exception.class, () -> {
            validator.validate(getInvalidPixKeyValueNull(), errors);
        });
    }


    @Test
    public void testValidationWithInvalidPixKeyTypeDTO() throws Exception {
        // Create an invalid PixKeyDTO
        // setting account number to null to make it invalid
        assertThrows(Exception.class, () -> {
            validator.validate(getInvalidPixKeyType(), errors);
        });
    }

    @Test
    public void testValidationWithInvalidAccountTypeDTO() throws Exception {
        // Create an invalid PixKeyDTO
        // setting account number to null to make it invalid
        assertThrows(Exception.class, () -> {
            validator.validate(getInvalidAccountTypeMock(), errors);
        });
    }

    @Test
    public void testValidationWithInvalidPersonTypeDTO() throws Exception {
        // Create an invalid PixKeyDTO
        // setting account number to null to make it invalid
        assertThrows(Exception.class, () -> {
            validator.validate(getInvalidPersonTypeMock(), errors);
        });
    }

    @Test
    public void testValidationWithInvalidAccountNumberDTO() throws Exception {
        // Create an invalid PixKeyDTO
        // setting account number to null to make it invalid
        assertThrows(Exception.class, () -> {
            validator.validate(getInvalidAccountNumberMock(), errors);
        });
    }

    @Test
    public void testValidationWithInvalidAccountNumberNullDTO() throws Exception {
        // Create an invalid PixKeyDTO
        // setting account number to null to make it invalid
        assertThrows(Exception.class, () -> {
            validator.validate(getInvalidAccountNumberNullMock(), errors);
        });
    }

    @Test
    public void testValidationWithInvalidAccountNumberEmptyDTO() throws Exception {
        // Create an invalid PixKeyDTO
        // setting account number to null to make it invalid
        assertThrows(Exception.class, () -> {
            validator.validate(getInvalidAccountNumberEmptyMock(), errors);
        });
    }


    @Test
    public void testValidationWithInvalidAgencyNumberDTO() throws Exception {
        // Create an invalid PixKeyDTO
        // setting account number to null to make it invalid
        assertThrows(Exception.class, () -> {
            validator.validate(getInvalidAgencyNumberMock(), errors);
        });
    }

    @Test
    public void testValidationWithInvalidAgencyNumberEmptyDTO() throws Exception {
        // Create an invalid PixKeyDTO
        // setting account number to null to make it invalid
        assertThrows(Exception.class, () -> {
            validator.validate(getInvalidAgencyNumberEmptyMock(), errors);
        });
    }

    @Test
    public void testValidationWithInvalidAgencyNumberNullDTO() throws Exception {
        // Create an invalid PixKeyDTO
        // setting account number to null to make it invalid
        assertThrows(Exception.class, () -> {
            validator.validate(getInvalidAgencyNumberNullMock(), errors);
        });
    }

    @Test
    public void testValidationWithInvalidAccountHolderNameDTO() throws Exception {
        // Create an invalid PixKeyDTO
        // setting account number to null to make it invalid
        assertThrows(Exception.class, () -> {
            validator.validate(getInvalidAccountHolderNameEmptyMock(), errors);
        });
    }

    @Test
    public void testValidationWithInvalidAccountHolderNameNullDTO() throws Exception {
        // Create an invalid PixKeyDTO
        // setting account number to null to make it invalid
        assertThrows(Exception.class, () -> {
            validator.validate(getInvalidAccountHolderNameNullMock(), errors);
        });
    }
}
