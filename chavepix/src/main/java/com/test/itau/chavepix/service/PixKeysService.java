package com.test.itau.chavepix.service;

import com.test.itau.chavepix.model.AccountPixKeysModel;
import com.test.itau.chavepix.dto.PixKeyDTO;
import com.test.itau.chavepix.model.KeyTypeModel;
import com.test.itau.chavepix.model.PersonTypeModel;
import com.test.itau.chavepix.model.PixKeyModel;
import com.test.itau.chavepix.persistence.entity.PixKeyEntity;
import com.test.itau.chavepix.persistence.repository.PixKeyRepository;
import com.test.itau.chavepix.validation.handler.PixKeyValidationHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PixKeysService {

    private final PixKeyRepository pixKeyRepository;
    private final PixKeyValidationHandler pixKeyValidationHandler;

    public PixKeyEntity createPixKey(PixKeyDTO pixKeyDTO) {

        AccountPixKeysModel accountPixKeys = findByAccountAndAgency(pixKeyDTO.getAgencyNumber(), pixKeyDTO.getAccountNumber());
        pixKeyValidationHandler.validatePixKey(accountPixKeys,pixKeyDTO);

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
