package com.test.itau.chavepix.service;

import com.test.itau.chavepix.model.AccountPixKeysModel;
import com.test.itau.chavepix.dto.PixKeyDTO;
import com.test.itau.chavepix.model.KeyTypeModel;
import com.test.itau.chavepix.model.PersonTypeModel;
import com.test.itau.chavepix.model.PixKeyModel;
import com.test.itau.chavepix.entity.PixKeyEntity;
import com.test.itau.chavepix.repository.PixKeyRepository;
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
            validatePixKeyCountLimit(accountPixKeys, pixKeyDTO);
            validatePersonType(accountPixKeys, pixKeyDTO);
            validateIfKeyValueIsUnique(accountPixKeys, pixKeyDTO);
        }

        return pixKeyRepository.save(new PixKeyEntity(pixKeyDTO));
    }

    private void validatePixKeyCountLimit(AccountPixKeysModel accountPixKeys, PixKeyDTO pixKey){
        if(accountPixKeys.hasKeysLimitBeenReached()){
            throw new RuntimeException("Pix Keys Limit Reached");
        }
    }

    private void validatePersonType(AccountPixKeysModel accountPixKeys, PixKeyDTO pixKey){
        if(!accountPixKeys.isValidPersonType(pixKey.getPersonTypeDTO())){
            throw new RuntimeException("Person Type Invalid");
        }
    }

    private void validateIfKeyValueIsUnique(AccountPixKeysModel accountPixKeys, PixKeyDTO pixKey){
        if(pixKeyRepository.existsByKeyValue(pixKey.getKeyValue())){
            throw new RuntimeException("Pix Keys Not Unique");
        }
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
