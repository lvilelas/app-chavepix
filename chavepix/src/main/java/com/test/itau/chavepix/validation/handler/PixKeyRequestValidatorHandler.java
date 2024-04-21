package com.test.itau.chavepix.validation.handler;

import com.test.itau.chavepix.dto.PixKeyDTO;
import org.springframework.validation.Errors;

public interface PixKeyRequestValidatorHandler {

    void validateRequest(PixKeyDTO target);

}
