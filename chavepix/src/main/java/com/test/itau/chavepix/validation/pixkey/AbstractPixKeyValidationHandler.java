package com.test.itau.chavepix.validation.pixkey;

import com.test.itau.chavepix.dto.PixKeyDTO;
import com.test.itau.chavepix.model.AccountPixKeysModel;
import com.test.itau.chavepix.validation.handler.PixKeyValidationHandler;

public abstract class AbstractPixKeyValidationHandler implements PixKeyValidationHandler {

    private AbstractPixKeyValidationHandler next;

    public AbstractPixKeyValidationHandler setNext(AbstractPixKeyValidationHandler next) {
        this.next = next;
        return next;
    }

    @Override
    public void validatePixKey(AccountPixKeysModel accountPixKeys, PixKeyDTO pixKey) {
        validate(accountPixKeys, pixKey);
        if (next != null) {
            next.validatePixKey(accountPixKeys, pixKey);
        }
    }

    protected abstract void validate(AccountPixKeysModel accountPixKeys, PixKeyDTO pixKey);
}