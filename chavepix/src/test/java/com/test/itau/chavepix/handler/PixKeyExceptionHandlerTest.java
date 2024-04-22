package com.test.itau.chavepix.handler;
import com.test.itau.chavepix.dto.ErrorDTO;
import com.test.itau.chavepix.exceptions.InvalidBusinessRule;
import com.test.itau.chavepix.exceptions.InvalidFieldException;
import com.test.itau.chavepix.exceptions.PixKeyNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.NotReadablePropertyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PixKeyExceptionHandlerTest {

    @Test
    public void testHandlerForNotInvalidFieldException() {
        PixKeyExceptionHandler handler = new PixKeyExceptionHandler();
        InvalidFieldException exception = new InvalidFieldException("campoXPTO");

        ErrorDTO result = handler.handler(exception);

        assertEquals("Field is not valid : campoXPTO",result.getMessage());
        assertEquals("erro na validação dos campos", result.getCause());

    }

    @Test
    public void testHandlerForInvalidBusinessRule() {
        PixKeyExceptionHandler handler = new PixKeyExceptionHandler();
        InvalidBusinessRule exception = new InvalidBusinessRule("campoXPTO");

        ErrorDTO result = handler.handler(exception);

        assertEquals("campoXPTO",result.getMessage());
        assertEquals("regra de negocio não atendida", result.getCause());
    }


    public void testHandlerForPixKeyNotFoundException() {
        PixKeyExceptionHandler handler = new PixKeyExceptionHandler();
        PixKeyNotFoundException exception = new PixKeyNotFoundException("campoXPTO");

        ErrorDTO result = handler.handler(exception);

        assertEquals("campoXPTO",result.getMessage());
        assertEquals("campoXPTO", result.getCause());
    }

}