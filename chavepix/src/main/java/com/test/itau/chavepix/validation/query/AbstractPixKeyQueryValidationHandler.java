package com.test.itau.chavepix.validation.query;

import com.test.itau.chavepix.validation.handler.PixKeyQueryValidationHandler;

import java.util.Map;

public abstract class AbstractPixKeyQueryValidationHandler implements PixKeyQueryValidationHandler {

    private AbstractPixKeyQueryValidationHandler next;

    public AbstractPixKeyQueryValidationHandler setNext(AbstractPixKeyQueryValidationHandler next) {
        this.next = next;
        return next;
    }

    @Override
    public void validatePixKeyQuery(Map<String,String> map) {
        validate(map);
        if (next != null) {
            next.validatePixKeyQuery(map);
        }
    }

    protected abstract void validate(Map<String,String> map);
}
