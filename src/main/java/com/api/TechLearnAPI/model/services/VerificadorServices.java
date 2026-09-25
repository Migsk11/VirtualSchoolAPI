package com.api.TechLearnAPI.model.services;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.TechLearnAPI.model.entity.Usuario;
import com.api.TechLearnAPI.model.entity.Verificador;
import com.api.TechLearnAPI.model.repository.UsuarioRepository;
import com.api.TechLearnAPI.model.repository.VerificadorRepository;





@Service
public class VerificadorServices {
    
    @Autowired
    private VerificadorRepository verificadorRepository;


    @Autowired
    private UsuarioRepository usuarioRepository;


    @Autowired
    private EmailSenderServices emailSenderServices;
    

    private Logger log = LoggerFactory.getLogger(VerificadorServices.class);




    public Boolean VerificarKeyMethod(String keyValidation){

        try {

            return verificadorRepository.existsByKeyValidation(Long.parseLong(keyValidation));
            
        } catch (IllegalArgumentException e) {
            
            return false;
        }

    }


    
    public Verificador GerarVerificador(String email){

        Verificador verificador = new Verificador();


        Usuario usuario = usuarioRepository.findByEmail(email)
            .orElseThrow();


        if(verificadorRepository.existsByUsuarioId(usuario.getId())){
            throw new RuntimeException("A sua chave ja foi enviada... aguarde 15 muitos para tentar novamente.");
        }
        else{

            Long keyGenerator = ThreadLocalRandom.current().nextLong(10000, 100000);

            verificador.setKeyValidation(keyGenerator);
            verificador.setUsuario(usuario);
            verificador.setDateExpire(LocalDateTime.now().plusMinutes(15));
            

            emailSenderServices.sendMail(email, verificador.getKeyValidation());

            
            return verificadorRepository.save(verificador);
        }

    }
    
    
}
