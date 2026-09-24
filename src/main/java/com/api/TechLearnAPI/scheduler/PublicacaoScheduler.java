package com.api.TechLearnAPI.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.api.TechLearnAPI.model.services.PublicacaoServices;

// Componente que gerencia a execução de tarefas agendadas (scheduled tasks)
// Spring automaticamente detecta métodos com @Scheduled e os executa nos intervalos definidos
@Component
public class PublicacaoScheduler {

    private static final Logger log = LoggerFactory.getLogger(VerificadorScheduler.class);

    @Autowired
    private PublicacaoServices publicacaoServices;

    // Método agendado que executa automaticamente todos os dias às 00:00 (meia-noite)
    // 
    // Explicação do cron: "0 0 0 * * ?"
    // - Primeiro 0 = segundos (0)
    // - Segundo 0 = minutos (0)
    // - Terceiro 0 = horas (0 = 00:00)
    // - * = todo dia do mês
    // - * = todo mês
    // - ? = ignora dia da semana
    //
    // Alternativa para outros intervalos:
    // - "0 0 */6 * * ?" = a cada 6 horas
    // - "0 0 12 * * ?" = todos os dias ao meio-dia
    // - "0 */30 * * * ?" = a cada 30 minutos
    @Scheduled(cron = "0 0 0 * * ?")
    public void expirarPublicacoesAgendadas() {
        try {
            // Log para rastrear quando a tarefa foi iniciada
            // System.out.println("[SCHEDULER] Iniciando verificação de publicações expiradas...");
            
            // Chama o serviço para deletar publicações com mais de 90 dias
            // A lógica de calcular 90 dias e executar a deleção está encapsulada no Service
            publicacaoServices.expirarPublicacoesAntigas();
            
            // Log de sucesso
            // log.info("[SCHEDULER] Verificação de expiração concluída com sucesso!");
            
        } catch (Exception e) {
            // Captura qualquer erro que ocorra durante a execução
            // Mantém a aplicação estável mesmo se a tarefa falhar
            // log.error("[SCHEDULER] Erro ao processar expiração de publicações: {}", e.getMessage(), e);
        }
    }
}
