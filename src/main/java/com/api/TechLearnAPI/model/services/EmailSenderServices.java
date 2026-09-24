package com.api.TechLearnAPI.model.services;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;






@Service 
public class EmailSenderServices {


    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}") 
    private String remetente;



    public EmailSenderServices(JavaMailSender mailSender){
        this.mailSender = mailSender;
        
    }


    public void sendMail(String email, UUID uuid){

        final String mailContentText = 
        "Verifique seu acesso com a seguinte chave: \n" + 
        uuid + 
        "\n\n" + 
        "Copie (Crtl + C) e cole (Crtl + V) no campo escrito 'Cole a chave aqui'...";


        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(remetente);
        mailMessage.setTo(email);
        mailMessage.setSentDate(new Date());
        mailMessage.setSubject("Verifique seu acesso:");
        mailMessage.setText(mailContentText);

        mailSender.send(mailMessage);
    }
    



}
