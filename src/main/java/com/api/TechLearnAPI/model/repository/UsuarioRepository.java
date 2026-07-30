package com.api.TechLearnAPI.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.TechLearnAPI.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findBySenha(String senha);
}
