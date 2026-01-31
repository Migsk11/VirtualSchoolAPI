package com.api.TechLearnAPI.model.repository;

import com.api.TechLearnAPI.model.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Long> {
}
