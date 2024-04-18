package com.test.itau.chavepix.configuration;
import com.test.itau.chavepix.validation.ChavePixValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class AppConfig {

    @Bean
    public Validator validatorChavePix() {
        return new ChavePixValidator();
    }

// Other configuration beans and methods...
}