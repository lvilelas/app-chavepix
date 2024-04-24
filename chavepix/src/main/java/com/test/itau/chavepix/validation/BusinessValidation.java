package com.test.itau.chavepix.validation;

import com.test.itau.chavepix.domain.PersonType;
import com.test.itau.chavepix.domain.PixKey;
import com.test.itau.chavepix.dto.PersonTypeDTO;
import com.test.itau.chavepix.persistence.entity.PixKeyEntity;
import com.test.itau.chavepix.persistence.repository.PixKeyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@AllArgsConstructor
public final class BusinessValidation {

    private final PixKeyRepository pixKeyRepository;

    public void validatePixKey(List<PixKey> pixKeyList, String keyType){
        if(!pixKeyList.isEmpty()) {
            validateIfDocumentAlredyExists(pixKeyList, keyType);
            validateIfKeysLimitBeenReached(pixKeyList);
            validateIfDocumentIsCorrect(pixKeyList, keyType);
        }
    }

    public void validatePixKeyNonUnique(PixKey pixKey){
        if(pixKeyRepository.existsByKeyValue(pixKey.getKeyValue())) {
            throw new RuntimeException("pix non unique");
        }
    }

    private void validateIfDocumentAlredyExists(List<PixKey> listPixKeys, String keyType){
        if(hasDocumentRegistered(keyType,listPixKeys)){
            throw new RuntimeException("document already registered");
        }
    }

    private void validateIfKeysLimitBeenReached(List<PixKey> pixKeys){
        if(hasKeysLimitBeenReached(pixKeys)){
            throw new RuntimeException("keys limit reached");
        }
    }

    public void validateIfDocumentIsCorrect(List<PixKey> listPixKeys,String personType){
        PixKey pixKey = listPixKeys.get(0);
        if(!personType.equals(pixKey.getPersonType().name())){
            throw new RuntimeException("Cannot put a pixkey with accountType: "+personType+" in a account type: "+pixKey.getPersonType().name());
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

    public boolean hasDocumentRegistered(String keyType, List<PixKey> listPixKeys){
        if(keyType.equals("CPF") || keyType.equals("CNPJ")) {
            return listPixKeys.stream().anyMatch(pixKeyModel -> pixKeyModel.getKeyType().name().equals("CPF")||pixKeyModel.getKeyType().name().equals("CNPJ"));
        }
        return false;
    }

    public boolean hasKeysLimitBeenReached(List<PixKey> listPixKeys) {
        PixKey pixKey = listPixKeys.get(0);
        if(pixKey.getPersonType().equals(PersonType.valueOf(PersonTypeDTO.FISICA.name()))){
            return listPixKeys.size()>=5;
        }
        return listPixKeys.size()>=20;
    }

}
