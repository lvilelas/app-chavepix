package com.test.itau.chavepix.handler;

import br.com.fluentvalidator.context.Error;
import br.com.fluentvalidator.context.ValidationResult;
import com.test.itau.chavepix.dto.ErrorResponseDTO;
import com.test.itau.chavepix.exceptions.BusinessRulesException;
import com.test.itau.chavepix.exceptions.PixKeyException;
import com.test.itau.chavepix.exceptions.PixKeyNotFoundException;
import com.test.itau.chavepix.mocks.PixKeyDTOMocks;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PixKeyExceptionHandlerTest extends PixKeyDTOMocks {

    @Test
    public void testPixKeyExceptionHandling() {
        PixKeyExceptionHandler handler = new PixKeyExceptionHandler();

        Collection<Error> collections = new ArrayList<Error>();

        Error error =  Error.create("teste","teste","teste",getInvalidAccountNumberMock());

        collections.add(error);

        PixKeyException exception = new PixKeyException(ValidationResult.fail(collections));

        ErrorResponseDTO response = handler.handler(exception);

        assertEquals("Error while validating fields", response.getMessage());
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, HttpStatus.valueOf(422));
        assertEquals(1, response.getErrors().size());
        assertEquals("teste", response.getErrors().get(0).getFieldName());
    }

    @Test
    public void testBusinessRulesExceptionHandling() {
        PixKeyExceptionHandler handler = new PixKeyExceptionHandler();
        BusinessRulesException exception = new BusinessRulesException("teste","Business rule error");

        ErrorResponseDTO response = handler.handler(exception);

        assertEquals("Error while validating fields", response.getMessage());
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, HttpStatus.valueOf(422));
        assertEquals(1, response.getErrors().size());
        assertEquals("Business rule error", response.getErrors().get(0).getError());
    }

    @Test
    public void testPixKeyNotFoundExceptionHandling() {
        PixKeyExceptionHandler handler = new PixKeyExceptionHandler();
        PixKeyNotFoundException exception = new PixKeyNotFoundException();

        handler.handler(exception);
        // Since the method returns void, we don't need to assert anything here.
    }
}
