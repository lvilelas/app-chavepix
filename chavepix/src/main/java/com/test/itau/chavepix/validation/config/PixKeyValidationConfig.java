package com.test.itau.chavepix.validation.config;

import com.test.itau.chavepix.persistence.repository.PixKeyRepository;
import com.test.itau.chavepix.validation.*;
import com.test.itau.chavepix.validation.handler.PixKeyValidationHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PixKeyValidationConfig {
    @Bean
    public PixKeyValidationHandler createChain(PixKeyRepository pixKeyRepository) {
        ValidatePixKeyCountLimit chain = new ValidatePixKeyCountLimit();

        chain.setNext(new ValidatePersonType())
                .setNext(new ValidateIfDocumentsAlreayExist())
                .setNext(new ValidateIfKeyValueIsUnique(pixKeyRepository))
                .setNext(new ValidatePixKeyType());


        return chain;
    }
}
