package com.test.itau.chavepix.handler;


import com.test.itau.chavepix.dto.ErrorResponseDTO;
import com.test.itau.chavepix.exceptions.BusinessRulesException;
import com.test.itau.chavepix.exceptions.PixKeyException;
import com.test.itau.chavepix.exceptions.PixKeyNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collections;

@ControllerAdvice
public class PixKeyExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(PixKeyExceptionHandler.class);

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    @ExceptionHandler(PixKeyException.class)
    public ErrorResponseDTO handler(PixKeyException ex){
        log.error("Validation error : {}", ex.getErrors());
        return new ErrorResponseDTO("Error while validating fields", ex.getErrors());
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    @ExceptionHandler(BusinessRulesException.class)
    public ErrorResponseDTO handler(BusinessRulesException ex){
        log.error("Business rule error : {}", ex.getErrorDTO().getError());
        return new ErrorResponseDTO("Error while validating fields", Collections.singletonList(ex.getErrorDTO()));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    @ExceptionHandler(PixKeyNotFoundException.class)
    public void handler(PixKeyNotFoundException ex){
        log.error("Error while searching for pix key : {}", ex.getMessage());
    }

}
