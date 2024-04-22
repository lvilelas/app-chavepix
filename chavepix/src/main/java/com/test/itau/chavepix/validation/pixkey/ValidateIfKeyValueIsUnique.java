package com.test.itau.chavepix.validation.pixkey;

import com.test.itau.chavepix.dto.PixKeyDTO;
import com.test.itau.chavepix.exceptions.InvalidBusinessRule;
import com.test.itau.chavepix.model.AccountPixKeysModel;
import com.test.itau.chavepix.persistence.repository.PixKeyRepository;

public class ValidateIfKeyValueIsUnique extends AbstractPixKeyValidationHandler {

    private final PixKeyRepository pixKeyRepository;

    public ValidateIfKeyValueIsUnique(PixKeyRepository pixKeyRepository) {
        this.pixKeyRepository = pixKeyRepository;
    }

    @Override
    public void validate(AccountPixKeysModel accountPixKeys, PixKeyDTO pixKey) {
        if(pixKeyRepository.existsByKeyValue(pixKey.getKeyValue())){
            throw new InvalidBusinessRule("Pix Keys Not Unique");
        }
    }
}
