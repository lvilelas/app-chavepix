package com.test.itau.chavepix.validation;

import com.test.itau.chavepix.dto.PixKeyDTO;
import com.test.itau.chavepix.model.AccountPixKeysModel;
import com.test.itau.chavepix.persistence.repository.PixKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidateIfKeyValueIsUnique extends AbstractPixKeyValidationHandler {

    private final PixKeyRepository pixKeyRepository;

    public ValidateIfKeyValueIsUnique(PixKeyRepository pixKeyRepository) {
        this.pixKeyRepository = pixKeyRepository;
    }

    @Override
    protected void validate(AccountPixKeysModel accountPixKeys, PixKeyDTO pixKey) {
        if(pixKeyRepository.existsByKeyValue(pixKey.getKeyValue())){
            throw new RuntimeException("Pix Keys Not Unique");
        }
    }
}
