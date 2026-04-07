package com.api.TechLearnAPI.controller;

import com.api.TechLearnAPI.model.entity.Usuario;
import com.api.TechLearnAPI.model.repository.UsuarioRepository;
import com.api.TechLearnAPI.model.services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UsuarioServices usuarioServices;


    //GET QUANT USUARIOS
    @GetMapping("/quantiaUsuarios")
    public ResponseEntity<Long> QuantiaUsuarios(){
        return ResponseEntity.ok(usuarioServices.ContarUsuarios());
    }



    //GET
    @GetMapping
    public ResponseEntity<List<Usuario>> ListarTodos(){
        return ResponseEntity.ok(usuarioRepository.findAll());
    }

    //GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> listarProdutoPorId(@PathVariable String id) {
        try {
            return ResponseEntity.ok(usuarioServices.findById(Long.parseLong((id))));
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body(
                    Map.of(
                            "status", 400,
                            "retorno", "Bad Request",
                            "message", "O id informado não é valido: " + id
                    )
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(
                    Map.of(
                            "status", 404,
                            "retorno", "Not Found",
                            "message", "Usuario não encontrado com o ID: " + id
                    )
            );
        }
    }

    //POST
    @PostMapping("/auth/register")
    public ResponseEntity<Usuario> SalvarUsuario(@RequestBody Usuario usuario) {
        Usuario novo = usuarioRepository.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(novo);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> DeletarUsuario(@PathVariable String id) {
        return usuarioServices.deleteById(id);
    }


    //ATUALIZAR
    @PutMapping("/{id}")
    public ResponseEntity<Object> AtualizarUsuario(@PathVariable String id, @RequestBody Usuario usuario) {
        try{
            return ResponseEntity.ok(usuarioServices.update(Long.parseLong(id), usuario));
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body(
                    Map.of(
                            "status", 400,
                            "retorno", "Bad Request",
                            "message", "Caminho informado inválido"
                    ));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(
                    Map.of(
                            "status", 404,
                            "retorno", "Not Found",
                            "message", "Usuario não encontrado com o ID: " + id
                    ));
        }
    }
}
