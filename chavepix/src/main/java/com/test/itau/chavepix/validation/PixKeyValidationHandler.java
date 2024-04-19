package com.test.itau.chavepix.validation;

import com.test.itau.chavepix.dto.PixKeyDTO;
import com.test.itau.chavepix.model.AccountPixKeysModel;
import com.test.itau.chavepix.persistence.repository.PixKeyRepository;

public interface PixKeyValidationHandler {

    void setNextChain(PixKeyValidationHandler nextChain);

    void validatePixKey(AccountPixKeysModel accountPixKeys, PixKeyDTO pixKey, PixKeyRepository pixKeyRepository);
}
