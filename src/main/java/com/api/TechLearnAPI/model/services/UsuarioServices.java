package com.api.TechLearnAPI.model.services;

import com.api.TechLearnAPI.model.entity.Usuario;
import com.api.TechLearnAPI.model.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UsuarioServices {

    @Autowired
    private UsuarioRepository usuarioRepository;




    //Listar todos os usuarios
    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
    }


    //Criar um novo usuario
    public Usuario save(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    // Listar Produto por ID
    public Usuario findById(Long id){
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("usuario nao encontrado com o id " + id));
    }

    // Deletar Usuario
    public boolean idExists(String id) {
        return usuarioRepository.existsById(Long.parseLong(id));
    }
    public ResponseEntity<Object> deleteById(String id){

        try{
            if(idExists((id))){
                usuarioRepository.deleteById(Long.parseLong(id));
                return ResponseEntity.ok().body(
                        Map.of(
                                "status", 200,
                                "retorno", "OK",
                                "message", "Usuario deletado com o ID: " + id
                        ));
            }
            else{
                return ResponseEntity.status(404).body(
                        Map.of(
                                "status", 404,
                                "retorno", "Not Found",
                                "message", "Usuario não encontrado com o ID: " + id
                        ));
            }

        }
        catch(NumberFormatException e){
            return ResponseEntity.badRequest().body(
                    Map.of(
                            "status", 400,
                            "retorno", "Bad Request",
                            "message", "Caminho inválido"
                    ));
        }


    }

    // Atualizar Usuario
    public Usuario update(Long id, Usuario usuario){
        Usuario usuarioExistente = findById(id);
        usuarioExistente.setNome(usuario.getNome());
        usuarioExistente.setEmail(usuario.getEmail());
        usuarioExistente.setSenha(usuario.getSenha());
        return usuarioRepository.save(usuarioExistente);
    }
}
