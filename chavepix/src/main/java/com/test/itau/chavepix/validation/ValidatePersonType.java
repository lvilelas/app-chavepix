package com.test.itau.chavepix.validation;

import com.test.itau.chavepix.dto.PixKeyDTO;
import com.test.itau.chavepix.model.AccountPixKeysModel;
import com.test.itau.chavepix.persistence.repository.PixKeyRepository;

public class ValidatePersonType implements PixKeyValidationHandler{

    private PixKeyValidationHandler chain;

    @Override
    public void setNextChain(PixKeyValidationHandler nextChain) {
        this.chain = nextChain;
    }

    @Override
    public void validatePixKey(AccountPixKeysModel accountPixKeys, PixKeyDTO pixKey, PixKeyRepository pixKeyRepository) {
        if(!accountPixKeys.isValidPersonType(pixKey.getPersonTypeDTO())){
            throw new RuntimeException("Person Type Invalid");
        }
        if(chain!=null){
            chain.validatePixKey(accountPixKeys, pixKey,pixKeyRepository);
        }
    }
}
