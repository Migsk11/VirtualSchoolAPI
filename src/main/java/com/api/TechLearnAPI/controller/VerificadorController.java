package com.api.TechLearnAPI.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.TechLearnAPI.model.services.VerificadorServices;








@RestController
@RequestMapping("/api/v1/Verificador")
public class VerificadorController {


    
    private Logger log = LoggerFactory.getLogger(VerificadorServices.class);
    
    
        @Autowired
        private VerificadorServices verificadorServices;


    public record VerificadorRequest(String email) {}


    @PostMapping()
    public ResponseEntity<?> CriarUUID(@RequestBody VerificadorRequest data){
        String email = data.email();
        try {
            verificadorServices.GerarVerificador(email);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        
    }
    
}
