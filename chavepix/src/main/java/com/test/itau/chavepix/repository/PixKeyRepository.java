package com.test.itau.chavepix.repository;

import com.test.itau.chavepix.entity.PixKeyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PixKeyRepository extends JpaRepository<PixKeyEntity, UUID> {
    boolean existsByKeyValue(String keyValue);

    List<PixKeyEntity> findByAgencyNumberAndAccountNumber(String agencyNumber, String accountNumber);
}
