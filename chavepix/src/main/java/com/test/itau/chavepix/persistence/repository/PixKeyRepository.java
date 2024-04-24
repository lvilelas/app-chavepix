package com.test.itau.chavepix.persistence.repository;

import com.test.itau.chavepix.persistence.entity.PixKeyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface PixKeyRepository extends JpaRepository<PixKeyEntity, UUID> {
    boolean existsByKeyValueAndDateTimeDeleteIsNull(String keyValue);

    boolean existsByKeyValue(String keyValue);

    PixKeyEntity findByIdAndDateTimeDeleteIsNull(UUID id);

    List<PixKeyEntity> findByAgencyNumberAndAccountNumber(BigInteger agencyNumber, BigInteger accountNumber);


    @Query(value = "SELECT p from PixKeyEntity as p WHERE (:id is NULL or p.id = :id) " +
            "AND (:keyType is NULL or p.keyTypeEntity = :keyType) " +
            "AND (:agencyNumber is NULL or p.agencyNumber = :agencyNumber) " +
            "AND (:accountNumber is NULL or p.accountNumber = :accountNumber) " +
            "AND (:accountHolderName is NULL or p.accountHolderName = :accountHolderName)" +
            "AND (:dateTimeInclusaoInicio is NULL or p.dateTimeCreation between :dateTimeInclusaoInicio and :dateTimeInclusaoFim)" +
            "AND (:dateTimeExclusaoInicio is NULL or p.dateTimeDelete between :dateTimeExclusaoInicio and :dateTimeExclusaoFim)")
    List<PixKeyEntity> findCustom(UUID id,
                                  String keyType,
                                  String agencyNumber,
                                  String accountNumber,
                                  String accountHolderName,
                                  LocalDateTime dateTimeInclusaoInicio,
                                  LocalDateTime dateTimeInclusaoFim,
                                  LocalDateTime dateTimeExclusaoInicio,
                                  LocalDateTime dateTimeExclusaoFim);

}
