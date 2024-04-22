package com.test.itau.chavepix.validation.pixkey;

import com.test.itau.chavepix.dto.PixKeyDTO;
import com.test.itau.chavepix.exceptions.InvalidBusinessRule;
import com.test.itau.chavepix.model.AccountPixKeysModel;

public class ValidatePixKeyType extends AbstractPixKeyValidationHandler {


    @Override
    public void validate(AccountPixKeysModel accountPixKeys, PixKeyDTO pixKey) {
            switch (pixKey.getKeyTypeDTO())  {
                case CPF:
                    if(pixKey.getPersonTypeDTO().name().equals("JURIDICA")){
                        throw new InvalidBusinessRule("Pix Key Type dont match with Person Type : Juridica");
                    }
                    break;
                case CNPJ:
                    if(pixKey.getPersonTypeDTO().name().equals("FISICA")){
                        throw new InvalidBusinessRule("Pix Key Type dont match with Person Type : Fisica");
                    }
                    break;
            }

        }
}
