package com.test.itau.chavepix.validation.pixkey;

import com.test.itau.chavepix.dto.PixKeyDTO;
import com.test.itau.chavepix.model.AccountPixKeysModel;

public class ValidateIfDocumentsAlreayExist extends AbstractPixKeyValidationHandler {

    @Override
    public void validate(AccountPixKeysModel accountPixKeys, PixKeyDTO pixKey) {
        if(accountPixKeys!=null && accountPixKeys.hasDocumentRegistered(pixKey)){
            throw new RuntimeException("Pix Type Already Registered!");
        }
    }
}
