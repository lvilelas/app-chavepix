package com.test.itau.chavepix.service;

import com.test.itau.chavepix.model.AccountPixKeysModel;
import com.test.itau.chavepix.dto.PixKeyDTO;
import com.test.itau.chavepix.model.KeyTypeModel;
import com.test.itau.chavepix.model.PersonTypeModel;
import com.test.itau.chavepix.model.PixKeyModel;
import com.test.itau.chavepix.persistence.entity.PixKeyEntity;
import com.test.itau.chavepix.persistence.repository.PixKeyRepository;
import com.test.itau.chavepix.validation.ValidateIfDocumentsAlreayExist;
import com.test.itau.chavepix.validation.ValidateIfKeyValueIsUnique;
import com.test.itau.chavepix.validation.ValidatePersonType;
import com.test.itau.chavepix.validation.ValidatePixKeyCountLimit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PixKeysService {

    private final PixKeyRepository pixKeyRepository;

    public PixKeyEntity createPixKey(PixKeyDTO pixKeyDTO) {

        AccountPixKeysModel accountPixKeys = findByAccountAndAgency(pixKeyDTO.getAgencyNumber(), pixKeyDTO.getAccountNumber());

        if(accountPixKeys != null){
            ValidatePixKeyCountLimit validatePixKeyCountLimit = new ValidatePixKeyCountLimit();
            ValidatePersonType validatePersonType = new ValidatePersonType();
            ValidateIfDocumentsAlreayExist validateIfDocumentsAlreayExist = new ValidateIfDocumentsAlreayExist();

            validatePixKeyCountLimit.setNextChain(validatePersonType);
            validatePersonType.setNextChain(validateIfDocumentsAlreayExist);
            validatePixKeyCountLimit.validatePixKey(accountPixKeys,pixKeyDTO,pixKeyRepository);
        }

        ValidateIfKeyValueIsUnique validateIfKeyValueIsUnique = new ValidateIfKeyValueIsUnique();
        validateIfKeyValueIsUnique.validatePixKey(accountPixKeys,pixKeyDTO,pixKeyRepository);

        return pixKeyRepository.save(new PixKeyEntity(pixKeyDTO));
    }


    private AccountPixKeysModel findByAccountAndAgency(String agencyNumber, String accountNumber) {
        List<PixKeyEntity> pixKeys = pixKeyRepository.findByAgencyNumberAndAccountNumber(agencyNumber, accountNumber);

        if (pixKeys.isEmpty()) {
            return null;
        }
        PixKeyEntity pixKey = pixKeys.get(0);

        return AccountPixKeysModel.builder().accountNumber(pixKey.getAccountNumber())
                .agencyNumber(pixKey.getAgencyNumber())
                .personType(PersonTypeModel.valueOf(pixKey.getPersonTypeEntity().name()))
                .pixKeys(pixKeys.stream()
                        .map(pixKeyEntity -> PixKeyModel.builder()
                                .keyType(KeyTypeModel.valueOf(pixKeyEntity.getKeyTypeEntity().name()))
                                .keyValue(pixKeyEntity.getKeyValue())
                                .build()).toList())
                .build();
    }
}
