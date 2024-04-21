package com.test.itau.chavepix.validation.pixkey;

import com.test.itau.chavepix.dto.PixKeyDTO;
import com.test.itau.chavepix.helper.ReflectionAttributeMapper;
import com.test.itau.chavepix.model.AccountPixKeysModel;
import com.test.itau.chavepix.validation.handler.PixKeyRequestValidatorHandler;
import jakarta.validation.ConstraintViolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Set;

public abstract class AbstractPixKeyRequestValidationHandler implements PixKeyRequestValidatorHandler {


    ReflectionAttributeMapper reflectionAttributeMapper = new ReflectionAttributeMapper();

    private AbstractPixKeyRequestValidationHandler next;

    public AbstractPixKeyRequestValidationHandler setNext(AbstractPixKeyRequestValidationHandler next) {
        this.next = next;
        return next;
    }

    @Override
    public void validateRequest(PixKeyDTO pixKey) {
        validate(pixKey);
        if (next != null) {
            next.validateRequest(pixKey);
        }
    }

    protected abstract void validate(PixKeyDTO pixKey);

    protected Object getFieldValue(PixKeyDTO pixKey, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        return reflectionAttributeMapper.getFieldValue(pixKey, fieldName);
    }
}
