package com.test.itau.chavepix.validation;
import br.com.fluentvalidator.Validator;
import br.com.fluentvalidator.context.ValidationResult;
import com.test.itau.chavepix.domain.KeyType;
import com.test.itau.chavepix.dto.PixKeyQueryDTO;
import com.test.itau.chavepix.exceptions.PixKeyException;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class PixKeySearchValidationTest {

    @Test
    public void testValidateID() {
        Validator<PixKeyQueryDTO> validation = new PixKeySearchValidation();
        PixKeyQueryDTO dto = new PixKeyQueryDTO();
        dto.setId(UUID.randomUUID());
        Map<String,String> map = new HashMap<>();
        dto.setParameters(map);


        final ValidationResult result = validation.validate(dto);

       assertTrue(result.isValid());
    }

    @Test
    public void testValidateKeyType() {
        Validator<PixKeyQueryDTO> validation = new PixKeySearchValidation();
        PixKeyQueryDTO dto = new PixKeyQueryDTO();
        Map<String, String> parameters = new HashMap<>();
        parameters.put("tipo_chave", "EMAIL");
        dto.setParameters(parameters);

        final ValidationResult result = validation.validate(dto);

        assertTrue(result.isValid());
    }

    @Test
    public void testValidateDateCreate() {
        Validator<PixKeyQueryDTO> validation = new PixKeySearchValidation();
        PixKeyQueryDTO dto = new PixKeyQueryDTO();
        Map<String, String> parameters = new HashMap<>();
        parameters.put("data_inclusao", "20/01/2201");
        dto.setParameters(parameters);

        final ValidationResult result = validation.validate(dto);

        assertTrue(result.isValid());
    }

    @Test
    public void testValidateDateDelete() {
        Validator<PixKeyQueryDTO> validation = new PixKeySearchValidation();
        PixKeyQueryDTO dto = new PixKeyQueryDTO();
        Map<String, String> parameters = new HashMap<>();
        parameters.put("data_exclusao", "20/01/2201");
        dto.setParameters(parameters);

        final ValidationResult result = validation.validate(dto);

        assertTrue(result.isValid());
    }

    @Test
    public void testValidateDateTime() {
        Validator<PixKeyQueryDTO> validation = new PixKeySearchValidation();
        PixKeyQueryDTO dto = new PixKeyQueryDTO();
        Map<String, String> parameters = new HashMap<>();
        parameters.put("data_inclusao", "20/01/2201");
        parameters.put("data_exclusao", "20/01/2201");
        dto.setParameters(parameters);

        final ValidationResult result = validation.validate(dto);
        assertFalse(result.isValid());
    }

    @Test
    public void testValidateNumberOfParameters() {
        Validator<PixKeyQueryDTO> validation = new PixKeySearchValidation();
        PixKeyQueryDTO dto = new PixKeyQueryDTO();
        Map<String, String> parameters = new HashMap<>();
        parameters.put("param1", "value1");
        parameters.put("param2", "value2");
        parameters.put("param3", "value3");
        parameters.put("param4", "value4");
        parameters.put("param5", "value5");
        parameters.put("param6", "value6");
        parameters.put("param7", "value6");
        dto.setParameters(parameters);

        final ValidationResult result = validation.validate(dto);
        assertFalse(result.isValid());
    }
}
