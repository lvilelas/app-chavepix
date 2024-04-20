package com.test.itau.chavepix.validation;

import com.test.itau.chavepix.dto.PixKeyDTO;
import com.test.itau.chavepix.model.AccountPixKeysModel;
import com.test.itau.chavepix.persistence.repository.PixKeyRepository;

public class ValidatePixKeyCountLimit extends AbstractPixKeyValidationHandler{

    @Override
    public void validate(AccountPixKeysModel accountPixKeys, PixKeyDTO pixKey) {
        if(accountPixKeys!=null && accountPixKeys.hasKeysLimitBeenReached()){
            throw new RuntimeException("Pix Keys Limit Reached");
        }
    }
}
