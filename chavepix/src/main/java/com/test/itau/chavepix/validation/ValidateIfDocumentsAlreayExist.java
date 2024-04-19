package com.test.itau.chavepix.validation;

import com.test.itau.chavepix.dto.PixKeyDTO;
import com.test.itau.chavepix.model.AccountPixKeysModel;
import com.test.itau.chavepix.persistence.repository.PixKeyRepository;

public class ValidateIfDocumentsAlreayExist implements PixKeyValidationHandler{

    private PixKeyValidationHandler chain;
    @Override
    public void setNextChain(PixKeyValidationHandler nextChain) {
        this.chain = nextChain;
    }

    @Override
    public void validatePixKey(AccountPixKeysModel accountPixKeys, PixKeyDTO pixKey, PixKeyRepository pixKeyRepository) {
        if(accountPixKeys.hasDocumentRegistered(pixKey)){
            throw new RuntimeException("Pix Type Already Registered!");
        }
        if(chain!=null){
            chain.validatePixKey(accountPixKeys, pixKey, pixKeyRepository);
        }
    }
}
