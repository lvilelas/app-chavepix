package com.test.itau.chavepix.validation;

import com.test.itau.chavepix.dto.PixKeyDTO;
import com.test.itau.chavepix.model.AccountPixKeysModel;
import com.test.itau.chavepix.persistence.repository.PixKeyRepository;

public class ValidateIfDocumentsAlreayExist extends AbstractPixKeyValidationHandler {

    @Override
    protected void validate(AccountPixKeysModel accountPixKeys, PixKeyDTO pixKey) {
        if(accountPixKeys!=null && accountPixKeys.hasDocumentRegistered(pixKey)){
            throw new RuntimeException("Pix Type Already Registered!");
        }
    }
}
