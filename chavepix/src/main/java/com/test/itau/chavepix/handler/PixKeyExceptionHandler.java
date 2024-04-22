package com.test.itau.chavepix.handler;


import com.test.itau.chavepix.controller.PixKeyController;
import com.test.itau.chavepix.dto.ErrorDTO;
import com.test.itau.chavepix.exceptions.InvalidBusinessRule;
import com.test.itau.chavepix.exceptions.InvalidFieldException;
import com.test.itau.chavepix.exceptions.PixKeyNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.NotReadablePropertyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PixKeyExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(PixKeyExceptionHandler.class);

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    @ExceptionHandler(InvalidFieldException.class)
    public ErrorDTO handler(InvalidFieldException ex){
        log.error("Error while validating fields : {}", ex.getMessage());
        return new ErrorDTO("erro na validação dos campos",ex.getMessage());
    }


    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    @ExceptionHandler(InvalidBusinessRule.class)
    public ErrorDTO handler(InvalidBusinessRule ex){
        log.error("Error while validating business rules : {}", ex.getMessage());
        return new ErrorDTO("regra de negocio não atendida",ex.getMessage());
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    @ExceptionHandler(PixKeyNotFoundException.class)
    public ErrorDTO handler(PixKeyNotFoundException ex){
        log.error("Error while searching for pix key : {}", ex.getMessage());
        return new ErrorDTO("chave pix não encontrada",ex.getMessage());
    }
}
