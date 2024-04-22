package com.test.itau.chavepix.exceptions;

public class InvalidBusinessRule extends RuntimeException{

    public InvalidBusinessRule(String msg){
        super(msg);
    }
}
