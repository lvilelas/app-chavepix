package com.test.itau.chavepix.validation;

import com.test.itau.chavepix.dto.PixKeyDTO;
import com.test.itau.chavepix.model.AccountPixKeysModel;
import com.test.itau.chavepix.persistence.repository.PixKeyRepository;

public class ValidatePersonType extends AbstractPixKeyValidationHandler{

    @Override
    protected void validate(AccountPixKeysModel accountPixKeys, PixKeyDTO pixKey) {
        if(accountPixKeys!=null && !accountPixKeys.isValidPersonType(pixKey.getPersonTypeDTO())){
            throw new RuntimeException("Person Type Invalid");
        }
    }
}
