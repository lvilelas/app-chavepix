package com.test.itau.chavepix.repository;

import com.test.itau.chavepix.entity.ChavePixEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ChavePixReposotiry extends JpaRepository<ChavePixEntity, UUID> {
    boolean existsByValorChave(String valorChave);

    long countByNumeroAgenciaAndNumeroConta(String numeroAgencia, String numeroConta);
}
