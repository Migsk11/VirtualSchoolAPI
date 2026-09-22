package com.api.TechLearnAPI.model.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.TechLearnAPI.model.entity.Publicacao;
import com.api.TechLearnAPI.model.repository.PublicacaoRepository;

@Service
public class PublicacaoServices {

    @Autowired
    private PublicacaoRepository publicacaoRepository; 


    public void ValidarData(Date paramDate){
        Date currentDate = new Date();

        if (!paramDate.after(currentDate)){
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

    // Método para executar a expiração de publicações
    // Este método calcula a data limite (90 dias atrás) e deleta publicações antigas
    // Utiliza transação automática do @Transactional da query no repository
    public void expirarPublicacoesAntigas() {
        // Obtém a data/hora atual
        Calendar calendar = Calendar.getInstance();
        
        // Subtrai 90 dias da data atual
        // Calendar.DATE representa dias, então subtraímos 90
        calendar.add(Calendar.DATE, -90);
        
        // Converte para java.util.Date para comparação com a coluna do banco
        Date dataLimite = calendar.getTime();
        
        // Executa a query no repository que deleta todas as publicações
        // cuja data_publicacao é anterior à data limite calculada
        // Publicações com mais de 90 dias serão removidas
        publicacaoRepository.deletarPublicacoesAntigas(dataLimite);
        
        // Log para fins de auditoria (opcional)
        System.out.println("[EXPIRACAO] Publicações anteriores a " + dataLimite + " foram removidas.");
    }
}

