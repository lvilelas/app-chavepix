package com.test.itau.chavepix.handler;
import com.test.itau.chavepix.dto.ErrorDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.NotReadablePropertyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PixKeyExceptionHandlerTest {

    @Test
    public void testHandlerForNotReadablePropertyException() {
        PixKeyExceptionHandler handler = new PixKeyExceptionHandler();
        NotReadablePropertyException exception = new NotReadablePropertyException(DummyClass.class, "campoXPTO");

        ErrorDTO result = handler.handler(exception);

        assertEquals("campoXPTO",result.getMessage());
        assertEquals("erro na validação dos campos", result.getCause());

    }

    @Test
    public void testHandlerForRuntimeException() {
        PixKeyExceptionHandler handler = new PixKeyExceptionHandler();
        RuntimeException exception = new RuntimeException("campoXPTO");

        ErrorDTO result = handler.handler(exception);

        assertEquals("campoXPTO",result.getMessage());
        assertEquals("regra de negocio não atendida", result.getCause());
    }

    // Dummy class to simulate a property context
    private static class DummyClass {}
}