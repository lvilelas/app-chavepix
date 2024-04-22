package com.test.itau.chavepix.validation.config;

import com.test.itau.chavepix.persistence.repository.PixKeyRepository;
import com.test.itau.chavepix.validation.handler.PixKeyQueryValidationHandler;
import com.test.itau.chavepix.validation.handler.PixKeyRequestValidatorHandler;
import com.test.itau.chavepix.validation.handler.PixKeyValidationHandler;
import com.test.itau.chavepix.validation.pixkey.*;
import com.test.itau.chavepix.validation.query.ValidateEmptyQuery;
import com.test.itau.chavepix.validation.query.ValidateIdQuery;
import com.test.itau.chavepix.validation.query.ValidateDateQuery;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class PixKeyValidationConfig {
    @Bean
    public PixKeyQueryValidationHandler createPixValidationChain() {
        ValidateDateQuery chain = new ValidateDateQuery();

        chain.setNext(new ValidateIdQuery())
                .setNext(new ValidateEmptyQuery());

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


    @Bean
    @Primary
    public PixKeyRequestValidatorHandler createRequestChain(PixKeyRepository pixKeyRepository) {
        ValidateFieldNonNull chain = new ValidateFieldNonNull("accountNumber");

        chain.setNext(new ValidateFieldRegex("accountNumber","[0-9]{1,8}"))
                .setNext(new ValidateFieldNonNull("agencyNumber"))
                .setNext(new ValidateFieldRegex("agencyNumber","[0-9]{1,4}"))
                .setNext(new ValidateFieldNonNull("personTypeDTO"))
                .setNext(new ValidateFieldNonNull("accountTypeDTO"))
                .setNext(new ValidateFieldNonNull("keyTypeDTO"))
                .setNext(new ValidateFieldNonNull("accountHolderName"))
                .setNext(new ValidateFieldRegex("accountHolderName","[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]{1,30}"))
                .setNext(new ValidateFieldRegex("accountHolderLastName","[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]{0,30}"))
                .setNext(new ValidatePixKeyField());

        return chain;
    }

    @Bean
    public PixKeyRequestValidatorHandler createRequestUpdateChain(PixKeyRepository pixKeyRepository) {
        ValidateFieldNonNull chain = new ValidateFieldNonNull("accountNumber");

        chain.setNext(new ValidateFieldRegex("accountNumber","[0-9]{1,8}"))
                .setNext(new ValidateFieldNonNull("agencyNumber"))
                .setNext(new ValidateFieldRegex("agencyNumber","[0-9]{1,4}"))
                .setNext(new ValidateFieldNonNull("accountTypeDTO"))
                .setNext(new ValidateFieldNonNull("accountHolderName"))
                .setNext(new ValidateFieldRegex("accountHolderName","[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]{1,30}"))
                .setNext(new ValidateFieldRegex("accountHolderLastName","[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]{0,30}"));

        return chain;
    }
}
