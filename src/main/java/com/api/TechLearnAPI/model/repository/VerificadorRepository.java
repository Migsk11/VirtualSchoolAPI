package com.api.TechLearnAPI.model.repository;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.TechLearnAPI.model.entity.Verificador;

import jakarta.transaction.Transactional;

public interface VerificadorRepository extends JpaRepository<Verificador, UUID> {

    boolean existsByUsuarioId(Long id);

    @Transactional 
    void deleteByDateExpireBefore(LocalDateTime now);

    boolean existsByUuid(UUID uuid);

}
