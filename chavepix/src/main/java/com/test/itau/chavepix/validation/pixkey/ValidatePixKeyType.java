package com.test.itau.chavepix.validation.pixkey;

import com.test.itau.chavepix.dto.PixKeyDTO;
import com.test.itau.chavepix.model.AccountPixKeysModel;

public class ValidatePixKeyType extends AbstractPixKeyValidationHandler {


    @Override
    public void validate(AccountPixKeysModel accountPixKeys, PixKeyDTO pixKey) {
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

        }
}
