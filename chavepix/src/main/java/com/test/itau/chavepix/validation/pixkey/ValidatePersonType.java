package com.test.itau.chavepix.validation.pixkey;

import com.test.itau.chavepix.dto.PixKeyDTO;
import com.test.itau.chavepix.model.AccountPixKeysModel;

public class ValidatePersonType extends AbstractPixKeyValidationHandler{

    @Override
    public void validate(AccountPixKeysModel accountPixKeys, PixKeyDTO pixKey) {
        if(accountPixKeys!=null && !accountPixKeys.isValidPersonType(pixKey.getPersonTypeDTO())){
            throw new RuntimeException("Person Type Invalid");
        }
    }
}
