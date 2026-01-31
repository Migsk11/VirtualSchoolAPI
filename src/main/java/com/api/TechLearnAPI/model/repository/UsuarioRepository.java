package com.api.TechLearnAPI.model.repository;

import com.api.TechLearnAPI.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
