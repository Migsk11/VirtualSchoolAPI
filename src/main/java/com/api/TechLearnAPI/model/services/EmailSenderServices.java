package com.api.TechLearnAPI.model.services;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;






@Service 
public class EmailSenderServices {
    
    public EmailSenderServices(JavaMailSender mailSender){
        this.mailSender = mailSender;
            
    }


    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}") 
    private String remetente;




    private String carregarTemplate() {

        

        try {

            ClassPathResource resource =
                    new ClassPathResource("templates/email-validation.html");

            return new String(
                    resource.getInputStream().readAllBytes(),
                    StandardCharsets.UTF_8
            );

        }catch (IOException e) {
            throw new RuntimeException("Erro ao carregar template do e-mail", e);
        }

    }




    public void sendMail(String email, Long keyValidation){

        String htmlMail = carregarTemplate();



        String horaSolicitacao = LocalDateTime.now()
        .format(DateTimeFormatter.ofPattern("HH:mm"));
        
    
        htmlMail = htmlMail.replace("{{REQUEST_TIME}}", horaSolicitacao);
        htmlMail = htmlMail.replace("{{KEY_VALIDATION}}", keyValidation.toString());


        try {

            MimeMessage message = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(
                message,
                false,
                "UTF-8"
            );


            helper.setFrom(remetente);
            helper.setTo(email);
            helper.setSentDate(new Date());
            helper.setSubject("Verifique seu acesso");
            helper.setText(htmlMail, true);

            mailSender.send(message);

        }catch (MessagingException e) {
            throw new RuntimeException("Erro ao enviar e-mail", e);
        }
    }
    



}
