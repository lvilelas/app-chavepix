package com.test.itau.chavepix.exceptions;

public class InvalidFieldException extends RuntimeException{

    public InvalidFieldException(String msg){
        super("Field is not valid : "+msg);
    }
}
