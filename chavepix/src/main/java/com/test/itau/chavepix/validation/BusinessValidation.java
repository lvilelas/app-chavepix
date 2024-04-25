package com.test.itau.chavepix.validation;

import com.test.itau.chavepix.domain.KeyType;
import com.test.itau.chavepix.domain.PersonType;
import com.test.itau.chavepix.domain.PixKey;
import com.test.itau.chavepix.dto.PersonTypeDTO;
import com.test.itau.chavepix.exceptions.BusinessRulesException;
import com.test.itau.chavepix.persistence.repository.PixKeyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public final class BusinessValidation {

    private final PixKeyRepository pixKeyRepository;

    public void validatePixKey(PixKey pixKey) {
        validatePixKeyNonUnique(pixKey);
        validateIfPixKeyDocumentIsCorrect(pixKey);
    }

    public void validatePixKeyAccount(List<PixKey> pixKeyList, PixKey pixkey){
        if(!pixKeyList.isEmpty()) {
            validateIfDocumentAlredyExists(pixKeyList, pixkey.getKeyType().name());
            validateIfKeysLimitBeenReached(pixKeyList);
            validateIfDocumentAccountIsCorrect(pixKeyList, pixkey.getKeyType().name());
            validateIfPersonTypeIrCorrect(pixKeyList, pixkey.getPersonType().name());
        }
    }

    public void validatePixKeyNonUnique(PixKey pixKey){
        if(pixKeyRepository.existsByKeyValue(pixKey.getKeyValue())) {
            throw new BusinessRulesException(pixKey.getKeyValue(),"Pix Key Already Exists");
        }
    }

    void validateIfDocumentAlredyExists(List<PixKey> listPixKeys, String keyType){
        if(hasDocumentRegistered(keyType,listPixKeys)){
            throw new BusinessRulesException("CPF/CNPJ", "Document already registered for this ag/account");
        }
    }

    void validateIfKeysLimitBeenReached(List<PixKey> pixKeys){
        if(hasKeysLimitBeenReached(pixKeys)){
            throw new BusinessRulesException(pixKeys.size()+" - pix keys","keys limit reached");
        }
    }

    public void validateIfPersonTypeIrCorrect(List<PixKey> listPixKeys, String personType){
        PixKey pixKey = listPixKeys.get(0);
        if(!personType.equals(pixKey.getPersonType().name())){
            throw new BusinessRulesException(personType, "account "+ pixKey.getAgencyNumber()+"/"+pixKey.getAccountNumber()+" type is : "+pixKey.getPersonType().name());
        }
    }

    public void validateIfPixKeyDocumentIsCorrect(PixKey pixKey){
        if(doesNotPixKeyTypeMatchPersonType(pixKey.getKeyType().name(),pixKey.getPersonType().name())){
            throw new BusinessRulesException(pixKey.getKeyType().name(), "pix key type doesn't match account type: "+pixKey.getPersonType().name());
        }
    }

    public void validateIfDocumentAccountIsCorrect(List<PixKey> listPixKeys, String keyType){
        PixKey pixKey = listPixKeys.get(0);
        if(doesNotPixKeyTypeMatchPersonType(keyType,pixKey.getPersonType().name())){
            throw new BusinessRulesException(keyType, "pix key type doesn't match account type: "+pixKey.getPersonType().name());
        }
    }

    public boolean doesNotPixKeyTypeMatchPersonType(String pixKeyType, String personType){
        return (pixKeyType.equals("CPF") && personType.equals("JURIDICA"))
                || pixKeyType.equals("CNPJ") && personType.equals("FISICA");
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
