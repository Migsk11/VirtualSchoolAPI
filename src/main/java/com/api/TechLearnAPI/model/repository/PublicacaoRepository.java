package com.api.TechLearnAPI.model.repository;

import com.api.TechLearnAPI.model.entity.Publicacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicacaoRepository extends JpaRepository<Publicacao, Long> {
}
