package com.test.itau.chavepix.exceptions;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PixKeyNotFoundException extends RuntimeException {

    public PixKeyNotFoundException() {
        super("PixKey not found");
    }
}
