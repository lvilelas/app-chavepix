package com.test.itau.chavepix.persistence.repository;

import com.test.itau.chavepix.dto.PixKeyDTO;
import com.test.itau.chavepix.persistence.entity.PixKeyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public interface PixKeyRepository extends JpaRepository<PixKeyEntity, UUID> {
    boolean existsByKeyValue(String keyValue);

    List<PixKeyEntity> findByAgencyNumberAndAccountNumber(String agencyNumber, String accountNumber);


    @Query(value = "SELECT p from PixKeyEntity as p WHERE (:id is NULL or p.id = :id) " +
            "AND (:keyType is NULL or p.keyTypeEntity = :keyType) " +
            "AND (:agencyNumber is NULL or p.agencyNumber = :agencyNumber) " +
            "AND (:accountNumber is NULL or p.accountNumber = :accountNumber) " +
            "AND (:accountHolderName is NULL or p.accountHolderName = :accountHolderName)")
    List<PixKeyEntity> findCustom(UUID id,
                                  String keyType,
                                  String agencyNumber,
                                  String accountNumber,
                                  String accountHolderName);

}
