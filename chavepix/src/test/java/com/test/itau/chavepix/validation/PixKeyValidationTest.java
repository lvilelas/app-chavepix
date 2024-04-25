package com.test.itau.chavepix.validation;

import br.com.fluentvalidator.Validator;
import br.com.fluentvalidator.context.ValidationResult;
import com.test.itau.chavepix.dto.PixKeyDTO;
import com.test.itau.chavepix.mocks.PixKeyDTOMocks;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PixKeyValidationTest extends PixKeyDTOMocks {

    @Test
    public void testValidateKeyType() {
        Validator<PixKeyDTO> validation = new PixKeyValidation();
        PixKeyDTO dto = getValidCPFPixKeyMock();
        dto.setKeyType("CPF");

        final ValidationResult result = validation.validate(dto);

        assertTrue(result.isValid());
    }

    @Test
    public void testValidatePersonType() {
        Validator<PixKeyDTO> validation = new PixKeyValidation();
        PixKeyDTO dto = getValidCPFPixKeyMock();
        dto.setPersonType("FISICA");

        final ValidationResult result = validation.validate(dto);

        assertTrue(result.isValid());
    }


    @Test
    public void testValidateAgencyNumber() {
        Validator<PixKeyDTO> validation = new PixKeyValidation();
        PixKeyDTO dto = getValidCPFPixKeyMock();
        dto.setAgencyNumber(BigInteger.valueOf(1234));

        final ValidationResult result = validation.validate(dto);

        assertTrue(result.isValid());
    }

    @Test
    public void testValidateAccountNumber() {
        Validator<PixKeyDTO> validation = new PixKeyValidation();
        PixKeyDTO dto = getValidCPFPixKeyMock();
        dto.setAccountNumber(BigInteger.valueOf(12345678));

        final ValidationResult result = validation.validate(dto);

        assertTrue(result.isValid());
    }

    @Test
    public void testValidateAccountHolderName() {
        Validator<PixKeyDTO> validation = new PixKeyValidation();
        PixKeyDTO dto = getValidCPFPixKeyMock();
        dto.setAccountHolderName("John Doe");

        final ValidationResult result = validation.validate(dto);

        assertTrue(result.isValid());
    }

    @Test
    public void testValidateAccountHolderLastName() {
        Validator<PixKeyDTO> validation = new PixKeyValidation();
        PixKeyDTO dto = getValidCPFPixKeyMock();
        dto.setAccountHolderLastName("Doe");

        final ValidationResult result = validation.validate(dto);

        assertTrue(result.isValid());
    }

    @Test
    public void testValidatePixKey() {
        Validator<PixKeyDTO> validation = new PixKeyValidation();
        PixKeyDTO dto = getValidCPFPixKeyMock();
        dto.setKeyValue("test@gmail.com");
        dto.setKeyType("EMAIL");

        final ValidationResult result = validation.validate(dto);

        assertTrue(result.isValid());
    }

    @Test
    public void testValidateAccountType() {
        Validator<PixKeyDTO> validation = new PixKeyValidation();
        PixKeyDTO dto = getValidCPFPixKeyMock();
        dto.setAccountType("POUPANCA");

        final ValidationResult result = validation.validate(dto);

        assertTrue(result.isValid());
    }
}
