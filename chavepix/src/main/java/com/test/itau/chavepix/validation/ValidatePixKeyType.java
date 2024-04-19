package com.test.itau.chavepix.validation;

import com.test.itau.chavepix.dto.PixKeyDTO;
import com.test.itau.chavepix.model.AccountPixKeysModel;
import com.test.itau.chavepix.persistence.repository.PixKeyRepository;

public class ValidatePixKeyType implements PixKeyValidationHandler{

    private PixKeyValidationHandler chain;

    @Override
    public void setNextChain(PixKeyValidationHandler nextChain) {
        this.chain = nextChain;
    }

    @Override
    public void validatePixKey(AccountPixKeysModel accountPixKeys, PixKeyDTO pixKey, PixKeyRepository pixKeyRepository) {

        switch (pixKey.getKeyTypeDTO())  {
            case CPF:
                if(pixKey.getPersonTypeDTO().name().equals("JURIDICA")){
                    throw new RuntimeException("Pix Key Type not match with Person Type : Juridica");
                }
                break;
            case CNPJ:
                if(pixKey.getPersonTypeDTO().name().equals("FISICA")){
                    throw new RuntimeException("Pix Key Type not match with Person Type : Fisica");
                }
                break;
        }
        if(chain!=null) {
            chain.validatePixKey(accountPixKeys, pixKey, pixKeyRepository);
        }
    }
}
