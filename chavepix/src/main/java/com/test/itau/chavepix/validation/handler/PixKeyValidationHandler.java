package com.test.itau.chavepix.validation.handler;

import com.test.itau.chavepix.dto.PixKeyDTO;
import com.test.itau.chavepix.model.AccountPixKeysModel;

public interface PixKeyValidationHandler {

    void validatePixKey(AccountPixKeysModel accountPixKeys, PixKeyDTO pixKey);
}
