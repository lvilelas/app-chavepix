package com.test.itau.chavepix.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.itau.chavepix.controller.PixKeyController;
import com.test.itau.chavepix.dto.*;
import com.test.itau.chavepix.exceptions.InvalidBusinessRule;
import com.test.itau.chavepix.exceptions.PixKeyNotFoundException;
import com.test.itau.chavepix.model.AccountPixKeysModel;
import com.test.itau.chavepix.model.KeyTypeModel;
import com.test.itau.chavepix.model.PersonTypeModel;
import com.test.itau.chavepix.model.PixKeyModel;
import com.test.itau.chavepix.persistence.entity.PixKeyEntity;
import com.test.itau.chavepix.persistence.repository.PixKeyRepository;
import com.test.itau.chavepix.validation.handler.PixKeyQueryValidationHandler;
import com.test.itau.chavepix.validation.handler.PixKeyRequestValidatorHandler;
import com.test.itau.chavepix.validation.handler.PixKeyValidationHandler;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.NotReadablePropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PixKeysService {

    @Autowired
    private PixKeyRepository pixKeyRepository;

    @Autowired
    private PixKeyValidationHandler pixKeyValidationHandler;

    @Autowired
    private PixKeyQueryValidationHandler pixKeyQueryValidationHandler;

    @Autowired
    @Qualifier("createRequestChain")
    private PixKeyRequestValidatorHandler pixKeyRequestValidatorHandler;

    @Autowired
    @Qualifier("createRequestUpdateChain")
    private PixKeyRequestValidatorHandler pixKeyRequestUpdateValidatorHandler;

    private static final Logger log = LoggerFactory.getLogger(PixKeyController.class);

    public PixKeyOutDTO createPixKey(PixKeyDTO pixKeyDTO) {
        log.info("Creating pix key: {}", pixKeyDTO);

        pixKeyRequestValidatorHandler.validateRequest(pixKeyDTO);
        AccountPixKeysModel accountPixKeys = findByAccountAndAgency(pixKeyDTO.getAgencyNumber(), pixKeyDTO.getAccountNumber());
        pixKeyValidationHandler.validatePixKey(accountPixKeys,pixKeyDTO);
        PixKeyEntity pixKeyEntity = new PixKeyEntity(pixKeyDTO);
        pixKeyEntity.setDateTimeCreation(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));

        log.info("Saving pix key: {}", pixKeyDTO);
        return new PixKeyOutDTO(pixKeyRepository.save(new PixKeyEntity(pixKeyDTO)));
    }


    public List<PixQueryOutDTO> searchPixKey(PixKeyQueryDTO pixKeyQueryDTO, HttpServletResponse response) {
        log.info("Searching pix key: {}", pixKeyQueryDTO);
        Map<String,String> map = convertTOMap(pixKeyQueryDTO);
        pixKeyQueryValidationHandler.validatePixKeyQuery(map);

        List<PixKeyEntity> pixKeys =  pixKeyRepository.findCustom(pixKeyQueryDTO.getId(),pixKeyQueryDTO.getKeyType(),pixKeyQueryDTO.getAgencyNumber(), pixKeyQueryDTO.getAccountNumber(), pixKeyQueryDTO.getAccountHolderName());

        if(pixKeys.isEmpty()){
            response.setStatus(404);
            log.warn("No pix keys found with query: {}", pixKeyQueryDTO);
        }
        log.info("Searching {} pix keys returned with query: {}",pixKeys.size(),pixKeyQueryDTO);
        return  pixKeys.stream()
                .map(PixQueryOutDTO::new)
                .collect(Collectors.toList());
    }


    public PixKeyOutDTO updatePixKey(PixKeyDTO pixKeyDTO) {
        log.info("Updating pix key: {}", pixKeyDTO);
        pixKeyRequestUpdateValidatorHandler.validateRequest(pixKeyDTO);

        PixKeyEntity pixKeyEntity = pixKeyRepository.findById(pixKeyDTO.getId()).orElse(null);

        validateNonNullAndExists(pixKeyEntity,pixKeyDTO.getId());

        pixKeyEntity.updatePixKey(pixKeyDTO);
        log.info("Pix key updated: {}", pixKeyDTO);
        return new PixKeyOutDTO(pixKeyRepository.save(pixKeyEntity));
    }

    public PixKeyDeleteOutDTO deletePixKey(UUID id) {
        log.info("Deleting pix key: {}", id);
        PixKeyEntity pixKeyEntity = pixKeyRepository.findById(id).orElse(null);

        validateNonNullAndExists(pixKeyEntity,id);

        pixKeyEntity.setDateTimeDelete(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
        pixKeyRepository.save(pixKeyEntity);
        log.info("Pix key deleted: {}", id);
        return new PixKeyDeleteOutDTO(pixKeyEntity);

    }


    private void validateNonNullAndExists(PixKeyEntity pixKeyEntity, UUID id){
        if(pixKeyEntity == null) {
            throw new PixKeyNotFoundException("ID " + id + " not found");
        }
        if(pixKeyEntity.getDateTimeDelete() != null){
            throw new InvalidBusinessRule("pix key alreay deleted");
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

    private Map<String,String> convertTOMap (PixKeyQueryDTO pixKeyQueryDTO){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        Map<String, String> map = objectMapper
                .convertValue(pixKeyQueryDTO, new TypeReference<Map<String, String>>() {});

        return map;
    }

}
