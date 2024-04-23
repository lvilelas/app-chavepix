package com.test.itau.chavepix.validation;

import com.test.itau.chavepix.domain.PixKey;
import com.test.itau.chavepix.dto.PersonTypeDTO;
import com.test.itau.chavepix.model.AccountPixKeysModel;
import com.test.itau.chavepix.model.PersonTypeModel;
import com.test.itau.chavepix.persistence.repository.PixKeyRepository;
import org.springframework.stereotype.Component;

@Component
public final class BusinessValidation {

    public void validatePixKeyNonUnique(PixKeyRepository repository, PixKey pixKey){
        if(repository.existsByKeyValue(pixKey.getKeyValue())) {
            throw new RuntimeException("pix non unique");
        }
    }

    public void validateIfDocumentAlredyExists(AccountPixKeysModel accountPixKeys, PixKey pixKey){
        if(hasDocumentRegistered(pixKey,accountPixKeys)){
            throw new RuntimeException("document already registered");
        }
    }

    public void validateIfKeysLimitBeenReached(AccountPixKeysModel accountPixKeys){
        if(hasKeysLimitBeenReached(accountPixKeys)){
            throw new RuntimeException("keys limit reached");
        }
    }

    public void validateIfDocumentIsCorrect(AccountPixKeysModel accountPixKeys,PixKey pixKey){
        if(!pixKey.getPersonType().name().equals(accountPixKeys.getPersonType().name())){
            throw new RuntimeException("document is not correct");
        }
    }



    public boolean hasDocumentRegistered(PixKey pixKeyDTO, AccountPixKeysModel accountPixKeysModel){
        if(pixKeyDTO.getKeyType().name().equals("CPF") || pixKeyDTO.getKeyType().name().equals("CNPJ")) {
            return accountPixKeysModel.getPixKeys().stream().anyMatch(pixKeyModel -> pixKeyModel.getKeyType().name().equals("CPF")||pixKeyModel.getKeyType().name().equals("CNPJ"));
        }
        return false;
    }

    public boolean hasKeysLimitBeenReached(AccountPixKeysModel pixKeysModel) {
        if(pixKeysModel.getPersonType()== PersonTypeModel.valueOf(PersonTypeDTO.FISICA.name())){
            return pixKeysModel.getPixKeys().size()>=5;
        }
        return pixKeysModel.getPixKeys().size()>=20;
    }
}
