package com.test.itau.chavepix.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.itau.chavepix.controller.PixKeyController;
import com.test.itau.chavepix.domain.PixKey;
import com.test.itau.chavepix.dto.PixKeyDeleteOutDTO;
import com.test.itau.chavepix.dto.PixKeyQueryDTO;
import com.test.itau.chavepix.dto.PixQueryOutDTO;
import com.test.itau.chavepix.mapper.PixKeyMapper;
import com.test.itau.chavepix.mapper.PixKeyUpdateMapper;
import com.test.itau.chavepix.model.AccountPixKeysModel;
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
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PixKeysService {


    private final PixKeyRepository pixKeyRepository;

    private static final Logger log = LoggerFactory.getLogger(PixKeyController.class);

    private final BusinessValidation businessValidation;

    public PixKey createPixKey(PixKey pixKey) {

        log.info("Creating pix key: {}", pixKey);

        List<PixKey> pixKeys = findByAccountAndAgency(pixKey.getAgencyNumber(), pixKey.getAccountNumber());

        businessValidation.validatePixKeyNonUnique(pixKey);
        businessValidation.validatePixKey(pixKeys,pixKey.getPersonType().name());

        PixKeyEntity pixKeyEntity = PixKeyMapper.INSTANCE.toPixKeyEntity(pixKey);
        log.info("Saving pix key: {}", pixKey);

        return PixKeyMapper.INSTANCE.toPixKey(pixKeyRepository.save(pixKeyEntity)); //new PixKeyOutDTO(pixKeyRepository.save(new PixKeyEntity(pixKey)));
    }

    public List<PixQueryOutDTO> searchPixKey(PixKeyQueryDTO pixKeyQueryDTO) {
        log.info("Searching pix key: {}", pixKeyQueryDTO);

        List<PixKeyEntity> pixKeys =  pixKeyRepository.findCustom(pixKeyQueryDTO.getId(),
                pixKeyQueryDTO.getParameters().get("tipo_chave"),
                pixKeyQueryDTO.getParameters().get("numero_agencia"),
                pixKeyQueryDTO.getParameters().get("numero_conta"),
                pixKeyQueryDTO.getParameters().get("nome_correntista"),
                convertToLocalDate(pixKeyQueryDTO.getParameters().get("data_inclusao"),getFormaterStart()),
                convertToLocalDate(pixKeyQueryDTO.getParameters().get("data_inclusao"),getFormaterEnd()),
                convertToLocalDate(pixKeyQueryDTO.getParameters().get("data_exclusao"),getFormaterStart()),
                convertToLocalDate(pixKeyQueryDTO.getParameters().get("data_exclusao"),getFormaterEnd()));

        log.info("Searching {} pix keys returned with query: {}",pixKeys.size(),pixKeyQueryDTO);
        return  pixKeys.stream()
                .map(PixKeyMapper.INSTANCE::toPixQueryOutDTO)
                .collect(Collectors.toList());
    }


    public PixKey updatePixKey(PixKey pix) {
        log.info("Updating pix key: {}", pix);

        PixKeyEntity pixKeyEntity = Optional.of(pixKeyRepository.findByIdAndDateTimeDeleteIsNull(pix.getId())).orElseThrow();

        PixKey pixKey = PixKeyMapper.INSTANCE.toPixKey(pixKeyEntity);

        List<PixKey> pixKeys = findByAccountAndAgency(pixKey.getAgencyNumber(), pixKey.getAccountNumber());
        businessValidation.validatePixKey(pixKeys,pixKey.getPersonType().name());

        PixKeyEntity updatedPixKeyEntity = PixKeyUpdateMapper.INSTANCE.updatePixKeyEntityFromPixKey(pixKeyEntity,pixKey);
        log.info("Pix key updated: {}", pixKey);
        return PixKeyMapper.INSTANCE.toPixKey(pixKeyRepository.save(updatedPixKeyEntity));

    }

    public PixKeyDeleteOutDTO deletePixKey(UUID id) {
        log.info("Deleting pix key: {}", id);
        PixKeyEntity pixKeyEntity = Optional.of(pixKeyRepository.findByIdAndDateTimeDeleteIsNull(id)).orElseThrow();

        pixKeyEntity.setDateTimeDelete(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
        pixKeyRepository.save(pixKeyEntity);
        log.info("Pix key deleted: {}", id);
        return PixKeyMapper.INSTANCE.toPixKeyDeleteOutDTO(pixKeyEntity);
    }

    private List<PixKey> findByAccountAndAgency(BigInteger agencyNumber, BigInteger accountNumber) {
        List<PixKeyEntity> pixKeys = pixKeyRepository.findByAgencyNumberAndAccountNumber(agencyNumber, accountNumber);

        return pixKeys.stream().map(PixKeyMapper.INSTANCE::toPixKey).collect(Collectors.toList());
    }


    private DateTimeFormatter getFormaterStart(){
        return new DateTimeFormatterBuilder()
                .appendPattern("dd/MM/yyyy").parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                .toFormatter();
    }


    private DateTimeFormatter getFormaterEnd(){
        return new DateTimeFormatterBuilder()
                .appendPattern("dd/MM/yyyy").parseDefaulting(ChronoField.HOUR_OF_DAY, 23)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 59)
                .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 59)
                .toFormatter();
    }

    private LocalDateTime convertToLocalDate(String date,DateTimeFormatter formatter){
        if(Objects.isNull(date)){
            return null;
        }

        return LocalDateTime.parse(date,formatter);
    }

}
