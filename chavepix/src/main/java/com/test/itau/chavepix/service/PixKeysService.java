package com.test.itau.chavepix.service;

import com.test.itau.chavepix.controller.PixKeyController;
import com.test.itau.chavepix.domain.KeyType;
import com.test.itau.chavepix.domain.PixKey;
import com.test.itau.chavepix.domain.PixKeyQuery;
import com.test.itau.chavepix.dto.PixKeyDeleteOutDTO;
import com.test.itau.chavepix.dto.PixQueryOutDTO;
import com.test.itau.chavepix.exceptions.PixKeyNotFoundException;
import com.test.itau.chavepix.mapper.PixKeyMapper;
import com.test.itau.chavepix.mapper.PixKeyUpdateMapper;
import com.test.itau.chavepix.persistence.entity.KeyTypeEntity;
import com.test.itau.chavepix.persistence.entity.PixKeyEntity;
import com.test.itau.chavepix.persistence.repository.PixKeyRepository;
import com.test.itau.chavepix.validation.BusinessValidation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PixKeysService {


    private final PixKeyRepository pixKeyRepository;

    private static final Logger log = LoggerFactory.getLogger(PixKeyController.class);

    private final BusinessValidation businessValidation;

    public PixKey createPixKey(PixKey pixKey) {

        List<PixKey> pixKeys = findByAccountAndAgency(pixKey.getAgencyNumber(), pixKey.getAccountNumber());

        businessValidation.validatePixKey(pixKey);
        businessValidation.validatePixKeyAccount(pixKeys, pixKey);

        PixKeyEntity pixKeyEntity = PixKeyMapper.INSTANCE.toPixKeyEntity(pixKey);
        log.info("Saving pix key: {}", pixKey);

        return PixKeyMapper.INSTANCE.toPixKey(pixKeyRepository.save(pixKeyEntity));
    }

    public List<PixQueryOutDTO> searchPixKey(PixKeyQuery pixKeyQuery) {
        log.info("Searching pix key: {}", pixKeyQuery);


        List<PixKeyEntity> pixKeys = pixKeyRepository.findCustom(pixKeyQuery.getId(),
                                                                KeyTypeEntity.getByDescription(pixKeyQuery.getKeyType()),
                                                                pixKeyQuery.getAgencyNumber(),
                                                                pixKeyQuery.getAccountNumber(),
                                                                pixKeyQuery.getAccountHolderName(),
                                                                pixKeyQuery.getDateCreateStart(),
                                                                pixKeyQuery.getDateCreateEnd(),
                                                                pixKeyQuery.getDateDeleteStart(),
                                                                pixKeyQuery.getDateDeleteEnd());


        log.info("Searching {} pix keys returned with query: {}", pixKeys.size(), pixKeyQuery);
        return pixKeys.stream()
                .map(PixKeyMapper.INSTANCE::toPixQueryOutDTO)
                .collect(Collectors.toList());
    }


    public PixKey updatePixKey(PixKey newPixKey) {
        log.info("Updating pix key: {}", newPixKey);

        PixKeyEntity pixKeyEntity = Optional.ofNullable(pixKeyRepository.findByIdAndDateTimeDeleteIsNull(newPixKey.getId())).orElseThrow(PixKeyNotFoundException::new);

        PixKey oldPixKey = PixKeyMapper.INSTANCE.toPixKey(pixKeyEntity);
        if(!newPixKey.getAgencyNumber().equals(oldPixKey.getAgencyNumber()) || !newPixKey.getAccountNumber().equals(oldPixKey.getAccountNumber())) {
            List<PixKey> pixKeys = findByAccountAndAgency(newPixKey.getAgencyNumber(), newPixKey.getAccountNumber());
            businessValidation.validatePixKeyAccount(pixKeys, oldPixKey);
        }

        PixKeyEntity updatedPixKeyEntity = PixKeyUpdateMapper.INSTANCE.updatePixKeyEntityFromPixKey(pixKeyEntity, newPixKey);
        log.info("Pix key updated: {}", updatedPixKeyEntity);
        return PixKeyMapper.INSTANCE.toPixKey(pixKeyRepository.save(updatedPixKeyEntity));

    }

    public PixKeyDeleteOutDTO deletePixKey(UUID id) {
        log.info("Deleting pix key: {}", id);
        PixKeyEntity pixKeyEntity = Optional.ofNullable(pixKeyRepository.findByIdAndDateTimeDeleteIsNull(id)).orElseThrow(PixKeyNotFoundException::new);

        pixKeyEntity.setDateTimeDelete(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
        pixKeyRepository.save(pixKeyEntity);
        log.info("Pix key deleted: {}", id);
        return PixKeyMapper.INSTANCE.toPixKeyDeleteOutDTO(pixKeyEntity);
    }

    private List<PixKey> findByAccountAndAgency(BigInteger agencyNumber, BigInteger accountNumber) {
        List<PixKeyEntity> pixKeys = pixKeyRepository.findByAgencyNumberAndAccountNumber(agencyNumber, accountNumber);

        return pixKeys.stream().map(PixKeyMapper.INSTANCE::toPixKey).collect(Collectors.toList());
    }
}
