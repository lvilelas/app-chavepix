package com.test.itau.chavepix.validation;

import com.test.itau.chavepix.domain.PixKey;
import com.test.itau.chavepix.dto.PersonTypeDTO;
import com.test.itau.chavepix.model.AccountPixKeysModel;
import com.test.itau.chavepix.model.PersonTypeModel;
import com.test.itau.chavepix.persistence.entity.PixKeyEntity;
import com.test.itau.chavepix.persistence.repository.PixKeyRepository;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

@Component
public final class BusinessValidation {

    public void validatePixKeyNonUnique(PixKeyRepository repository, PixKey pixKey){
        if(repository.existsByKeyValue(pixKey.getKeyValue())) {
            throw new RuntimeException("pix non unique");
        }
    }

    public void validateIfDocumentAlredyExists(AccountPixKeysModel accountPixKeys, String keyType){
        if(hasDocumentRegistered(keyType,accountPixKeys)){
            throw new RuntimeException("document already registered");
        }
    }

    public void validateIfKeysLimitBeenReached(AccountPixKeysModel accountPixKeys){
        if(hasKeysLimitBeenReached(accountPixKeys)){
            throw new RuntimeException("keys limit reached");
        }
    }

    public void validateIfDocumentIsCorrect(AccountPixKeysModel accountPixKeys,String personType){
        if(!personType.equals(accountPixKeys.getPersonType().name())){
            throw new RuntimeException("Cannot put a pixkey with accountType: "+personType+" in a account type: "+accountPixKeys.getPersonType().name());
        }
    }

    public void validateNonNull(PixKeyEntity pixKeyEntity){
        if(Objects.isNull(pixKeyEntity)) {
            throw new RuntimeException("pix key not found");
        }
    }

    public void validateNonDeleted(PixKeyEntity pixKeyEntity){
        if(Objects.nonNull(pixKeyEntity.getDateTimeDelete())) {
            throw new RuntimeException("pix key already deleted");
        }
    }

    public boolean hasDocumentRegistered(String keyType, AccountPixKeysModel accountPixKeysModel){
        if(keyType.equals("CPF") || keyType.equals("CNPJ")) {
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
