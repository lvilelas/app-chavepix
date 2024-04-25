package com.test.itau.chavepix.validation;

import br.com.fluentvalidator.Validator;
import br.com.fluentvalidator.context.ValidationResult;
import com.test.itau.chavepix.dto.PixKeyUpdateDTO;
import com.test.itau.chavepix.mocks.PixKeyDTOMocks;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PixKeyUpdateValidationTest extends PixKeyDTOMocks {

    @Test
    public void testValidateID() {
        Validator<PixKeyUpdateDTO> validation = new PixKeyUpdateValidation();
        PixKeyUpdateDTO dto = getPixUpdated();
        dto.setId(UUID.randomUUID());
        dto.setAccountType("CORRENTE");
        final ValidationResult result = validation.validate(dto);

        assertTrue(result.isValid());
    }

    @Test
    public void testValidateAccountNumber() {
        Validator<PixKeyUpdateDTO> validation = new PixKeyUpdateValidation();
        PixKeyUpdateDTO dto = getPixUpdated();
        dto.setAccountNumber(BigInteger.valueOf(12345678));
        dto.setAccountType("CORRENTE");
        final ValidationResult result = validation.validate(dto);

        assertTrue(result.isValid());
    }

    @Test
    public void testValidateAgencyNumber() {
        Validator<PixKeyUpdateDTO> validation = new PixKeyUpdateValidation();
        PixKeyUpdateDTO dto = getPixUpdated();
        dto.setAgencyNumber(BigInteger.valueOf(1234));
        dto.setAccountType("CORRENTE");
        final ValidationResult result = validation.validate(dto);

        assertTrue(result.isValid());
    }

    @Test
    public void testValidateAccountType() {
        Validator<PixKeyUpdateDTO> validation = new PixKeyUpdateValidation();
        PixKeyUpdateDTO dto = getPixUpdated();
        dto.setAccountType("CORRENTE");

        final ValidationResult result = validation.validate(dto);

        assertTrue(result.isValid());
    }

    @Test
    public void testValidateAccountHolderName() {
        Validator<PixKeyUpdateDTO> validation = new PixKeyUpdateValidation();
        PixKeyUpdateDTO dto = getPixUpdated();
        dto.setAccountHolderName("John Doe");
        dto.setAccountType("CORRENTE");
        final ValidationResult result = validation.validate(dto);

        assertTrue(result.isValid());
    }

    @Test
    public void testValidateAccountHolderLastName() {
        Validator<PixKeyUpdateDTO> validation = new PixKeyUpdateValidation();
        PixKeyUpdateDTO dto = getPixUpdated();
        dto.setAccountHolderLastName("Doe");
        dto.setAccountType("CORRENTE");
        final ValidationResult result = validation.validate(dto);

        assertTrue(result.isValid());
    }
}
