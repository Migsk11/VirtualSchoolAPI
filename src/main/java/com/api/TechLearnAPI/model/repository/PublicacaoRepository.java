package com.api.TechLearnAPI.model.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.api.TechLearnAPI.model.entity.Publicacao;

public interface PublicacaoRepository extends JpaRepository<Publicacao, Long> {

    // Query que deleta publicações cuja data_publicacao é anterior à data passada como parâmetro
    // Utiliza @Modifying para indicar que é uma operação de modificação (DELETE)
    // Utiliza @Transactional para garantir que a operação seja executada em uma transação
    // Esta abordagem é mais eficiente que buscar e deletar registros um a um em Java
    @Modifying
    @Transactional
    @Query("DELETE FROM Publicacao p WHERE p.data_publicacao < :dataLimite")
    void deletarPublicacoesAntigas(LocalDate dataLimite);
}

