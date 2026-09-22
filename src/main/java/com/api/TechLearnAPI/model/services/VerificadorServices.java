package com.api.TechLearnAPI.model.services;

import java.time.LocalDateTime;
import java.util.UUID;

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
    


    
    public Verificador save(String email){



        Verificador verificador = new Verificador();

        Usuario usuario = usuarioRepository.findByEmail(email)
            .orElseThrow();

        if(verificadorRepository.existsByUsuarioId(usuario.getId())){
            throw new RuntimeException("O seu token ja foi enviado... aguarde 15 muitos para tentar novamente.");
        }
        else{
            verificador.setUuid(UUID.randomUUID());
            verificador.setUsuario(usuario);
            verificador.setDateExpire(LocalDateTime.now().plusMinutes(15));
            verificador.setStatus_uuid(true);
            

            return verificadorRepository.save(verificador);
        }

    }
    
    
}
