package com.test.itau.chavepix.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.itau.chavepix.controller.PixKeyController;
import com.test.itau.chavepix.domain.PixKey;
import com.test.itau.chavepix.dto.PixKeyQueryDTO;
import com.test.itau.chavepix.mapper.PixKeyMapper;
import com.test.itau.chavepix.mapper.PixKeyUpdateMapper;
import com.test.itau.chavepix.model.AccountPixKeysModel;
import com.test.itau.chavepix.model.KeyTypeModel;
import com.test.itau.chavepix.model.PersonTypeModel;
import com.test.itau.chavepix.model.PixKeyModel;
import com.test.itau.chavepix.persistence.entity.PixKeyEntity;
import com.test.itau.chavepix.persistence.repository.PixKeyRepository;
import com.test.itau.chavepix.validation.BusinessValidation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PixKeysService {


    private final PixKeyRepository pixKeyRepository;

    private static final Logger log = LoggerFactory.getLogger(PixKeyController.class);

    private final BusinessValidation businessValidation;

    public PixKey createPixKey(PixKey pixKey) {

        log.info("Creating pix key: {}", pixKey);

        AccountPixKeysModel accountPixKeys = findByAccountAndAgency(pixKey.getAgencyNumber(), pixKey.getAccountNumber());

        businessValidation.validatePixKeyNonUnique(pixKeyRepository,pixKey);

        if (Objects.nonNull(accountPixKeys)) {
            businessValidation.validateIfDocumentAlredyExists(accountPixKeys,pixKey.getKeyType().name());
            businessValidation.validateIfKeysLimitBeenReached(accountPixKeys);
            businessValidation.validateIfDocumentIsCorrect(accountPixKeys, pixKey.getPersonType().name());
        }

        PixKeyEntity pixKeyEntity = PixKeyMapper.INSTANCE.toPixKeyEntity(pixKey);
        pixKeyEntity.setDateTimeCreation(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));

        log.info("Saving pix key: {}", pixKey);

        return PixKeyMapper.INSTANCE.toPixKey(pixKeyRepository.save(pixKeyEntity)); //new PixKeyOutDTO(pixKeyRepository.save(new PixKeyEntity(pixKey)));
    }

//    public List<PixQueryOutDTO> searchPixKey(PixKeyQueryDTO pixKeyQueryDTO, HttpServletResponse response) {
//        log.info("Searching pix key: {}", pixKeyQueryDTO);
//        Map<String,String> map = convertTOMap(pixKeyQueryDTO);
//        pixKeyQueryValidationHandler.validatePixKeyQuery(map);
//
//        List<PixKeyEntity> pixKeys =  pixKeyRepository.findCustom(pixKeyQueryDTO.getId(),pixKeyQueryDTO.getKeyType(),pixKeyQueryDTO.getAgencyNumber(), pixKeyQueryDTO.getAccountNumber(), pixKeyQueryDTO.getAccountHolderName());
//
//        if(pixKeys.isEmpty()){
//            response.setStatus(404);
//            log.warn("No pix keys found with query: {}", pixKeyQueryDTO);
//        }
//        log.info("Searching {} pix keys returned with query: {}",pixKeys.size(),pixKeyQueryDTO);
//        return  pixKeys.stream()
//                .map(PixQueryOutDTO::new)
//                .collect(Collectors.toList());
//    }
//
//
    public PixKey updatePixKey(PixKey pixKey) {
        log.info("Updating pix key: {}", pixKey);

        PixKeyEntity pixKeyEntity = pixKeyRepository.findById(pixKey.getId()).orElse(null);

        businessValidation.validateNonNull(pixKeyEntity);
        businessValidation.validateNonDeleted(pixKeyEntity);

        AccountPixKeysModel accountPixKeys = findByAccountAndAgency(pixKey.getAgencyNumber(), pixKey.getAccountNumber());
        if(Objects.nonNull(accountPixKeys)) {
            businessValidation.validateIfKeysLimitBeenReached(accountPixKeys);
            businessValidation.validateIfDocumentAlredyExists(accountPixKeys,pixKeyEntity.getKeyTypeEntity().name());
            businessValidation.validateIfDocumentIsCorrect(accountPixKeys, pixKeyEntity.getPersonTypeEntity().name());
        }

        PixKeyEntity updatedPixKeyEntity = PixKeyUpdateMapper.INSTANCE.updatePixKeyEntityFromPixKey(pixKeyEntity,pixKey);
        log.info("Pix key updated: {}", pixKey);
        return PixKeyMapper.INSTANCE.toPixKey(pixKeyRepository.save(updatedPixKeyEntity));
    }
//
//    public PixKeyDeleteOutDTO deletePixKey(UUID id) {
//        log.info("Deleting pix key: {}", id);
//        PixKeyEntity pixKeyEntity = pixKeyRepository.findById(id).orElse(null);
//
//        validateNonNullAndExists(pixKeyEntity,id);
//
//        pixKeyEntity.setDateTimeDelete(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
//        pixKeyRepository.save(pixKeyEntity);
//        log.info("Pix key deleted: {}", id);
//        return new PixKeyDeleteOutDTO(pixKeyEntity);
//
//    }
//
//

//
//
    private AccountPixKeysModel findByAccountAndAgency(BigInteger agencyNumber, BigInteger accountNumber) {
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

    private Map<String,String> convertTOMap (PixKeyQueryDTO pixKeyQueryDTO){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        Map<String, String> map = objectMapper
                .convertValue(pixKeyQueryDTO, new TypeReference<Map<String, String>>() {});

        return map;
    }

}
