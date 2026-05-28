package com.api.TechLearnAPI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.TechLearnAPI.model.entity.Publicacao;
import com.api.TechLearnAPI.model.services.PublicacaoServices;

@RestController
@RequestMapping("/api/v1/publicacoes")
public class PublicacaoController {

    @Autowired
    private PublicacaoServices publicacaoServices;

    // POST - Criar uma nova publicação
    @PostMapping
    public ResponseEntity<Publicacao> criarPublicacao(@RequestBody Publicacao publicacao) {
        try {
            Publicacao novaPublicacao = publicacaoServices.save(publicacao);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaPublicacao);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // GET - Listar todas as publicações
    @GetMapping
    public ResponseEntity<List<Publicacao>> listarTodasPublicacoes() {
        List<Publicacao> publicacoes = publicacaoServices.findAll();
        return ResponseEntity.ok(publicacoes);
    }

    // DELETE - Deletar publicação por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarPublicacao(@PathVariable Long id) {
        try {
            publicacaoServices.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
