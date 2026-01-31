package com.api.TechLearnAPI.model.repository;

import com.api.TechLearnAPI.model.entity.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {
}
