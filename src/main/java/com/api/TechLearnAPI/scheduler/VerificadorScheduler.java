package com.api.TechLearnAPI.scheduler;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.api.TechLearnAPI.model.repository.VerificadorRepository;


@Component
public class VerificadorScheduler {

    private static final Logger log = LoggerFactory.getLogger(PublicacaoScheduler.class);

    @Autowired 
    private VerificadorRepository verificadorRepository;




    @Scheduled(cron = "*/30 * * * * ?")
    public void expirarTokenVerificador() {
        try {

            // System.out.println("[SCHEDULER] Iniciando verificação de publicações expiradas...");
            
            
            LocalDateTime now = LocalDateTime.now();

            verificadorRepository.deleteByDateExpireBefore(now);
            

            // log.info("[SCHEDULER] Verificação de expiração concluída com sucesso!");
            
        } catch (Exception e) {
            // log.error("[SCHEDULER] Erro ao processar expiração de publicações: {}", e.getMessage(), e);
        }
    }
}
