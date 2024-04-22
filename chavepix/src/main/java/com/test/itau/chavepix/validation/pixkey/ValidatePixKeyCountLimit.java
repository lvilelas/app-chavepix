package com.test.itau.chavepix.validation.pixkey;

import com.test.itau.chavepix.dto.PixKeyDTO;
import com.test.itau.chavepix.exceptions.InvalidBusinessRule;
import com.test.itau.chavepix.model.AccountPixKeysModel;

public class ValidatePixKeyCountLimit extends AbstractPixKeyValidationHandler{

    @Override
    public void validate(AccountPixKeysModel accountPixKeys, PixKeyDTO pixKey) {
        if(accountPixKeys!=null && accountPixKeys.hasKeysLimitBeenReached()){
            throw new InvalidBusinessRule("Pix Keys Limit Reached");
        }
    }
}
