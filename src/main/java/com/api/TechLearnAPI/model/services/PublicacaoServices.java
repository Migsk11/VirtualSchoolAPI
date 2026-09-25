package com.api.TechLearnAPI.model.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.TechLearnAPI.model.entity.Publicacao;
import com.api.TechLearnAPI.model.repository.PublicacaoRepository;

@Service
public class PublicacaoServices {

    @Autowired
    private PublicacaoRepository publicacaoRepository; 


    public void ValidarData(LocalDate paramDate){
        LocalDate currentDate = LocalDate.now();

        if (paramDate.isBefore(currentDate)){
            throw new RuntimeException("Data invalida...");
        }

    }




    // Criar e salvar uma nova publicação
    public Publicacao save(Publicacao publicacao) {
        return publicacaoRepository.save(publicacao);
    }

    // Listar todas as publicações
    public List<Publicacao> findAll() {
        return publicacaoRepository.findAll();
    }

    // Buscar publicação por ID
    public Publicacao findById(Long id) {
        return publicacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publicacao nao encontrada com o id " + id));
    }

    // Deletar publicação por ID
    public void deleteById(Long id) {
        if (publicacaoRepository.existsById(id)) {
            publicacaoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Publicacao nao encontrada com o id " + id);
        }
    }


    public void expirarPublicacoesAntigas() {

    LocalDate dataLimite = LocalDate.now().minusDays(90);

    publicacaoRepository.deletarPublicacoesAntigas(dataLimite);

    System.out.println(
        "[EXPIRACAO] Publicações anteriores a " + dataLimite + " foram removidas."
    );
}
}

