package com.api.TechLearnAPI.model.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.TechLearnAPI.model.entity.Verificador;

import jakarta.transaction.Transactional;

public interface VerificadorRepository extends JpaRepository<Verificador, Long> {

    boolean existsByUsuarioId(Long id);

    @Transactional 
    void deleteByDateExpireBefore(LocalDateTime now);

    boolean existsByKeyValidation(Long keyValidation);

}
