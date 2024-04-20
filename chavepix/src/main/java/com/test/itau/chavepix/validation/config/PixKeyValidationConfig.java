package com.test.itau.chavepix.validation.config;

import com.test.itau.chavepix.persistence.repository.PixKeyRepository;
import com.test.itau.chavepix.validation.handler.PixKeyQueryValidationHandler;
import com.test.itau.chavepix.validation.handler.PixKeyValidationHandler;
import com.test.itau.chavepix.validation.pixkey.*;
import com.test.itau.chavepix.validation.query.ValidateDateQuery;
import com.test.itau.chavepix.validation.query.ValidateIdQuery;
import com.test.itau.chavepix.validation.query.ValidateNullQuery;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PixKeyValidationConfig {
    @Bean
    public PixKeyQueryValidationHandler createPixValidationChain() {
        ValidateNullQuery chain = new ValidateNullQuery();

        chain.setNext(new ValidateIdQuery())
                .setNext(new ValidateDateQuery());

        return chain;
    }

    @Bean
    public PixKeyValidationHandler createQueryChain(PixKeyRepository pixKeyRepository) {
        ValidatePixKeyCountLimit chain = new ValidatePixKeyCountLimit();

        chain.setNext(new ValidatePersonType())
                .setNext(new ValidateIfDocumentsAlreayExist())
                .setNext(new ValidateIfKeyValueIsUnique(pixKeyRepository))
                .setNext(new ValidatePixKeyType());


        return chain;
    }
}
