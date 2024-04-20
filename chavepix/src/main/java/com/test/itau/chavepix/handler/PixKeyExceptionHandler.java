package com.test.itau.chavepix.handler;


import com.test.itau.chavepix.dto.ErrorDTO;
import org.springframework.beans.NotReadablePropertyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PixKeyExceptionHandler {

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    @ExceptionHandler(NotReadablePropertyException.class)
    public ErrorDTO handler(NotReadablePropertyException ex){
        return new ErrorDTO("erro na validação dos campos",ex.getPropertyName());
    }


    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    public ErrorDTO handler(RuntimeException ex){
        return new ErrorDTO("regra de negocio não atendida",ex.getMessage());
    }
}
