package com.api.TechLearnAPI.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.TechLearnAPI.model.entity.Usuario;
import com.api.TechLearnAPI.model.repository.UsuarioRepository;
import com.api.TechLearnAPI.model.services.UsuarioServices;

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


    public record EmailRequest(String email) {}

    //POST LOGIN
    @PostMapping("/login")
    public Boolean ReceberLogin(@RequestBody EmailRequest data) {
        String email = data.email();
        try {
            usuarioServices.findByEmail(email);
            return true /* foi encontrado o email */;
        } catch (Exception e) {
            return false /* nao foi encontrado o email */;
        }
        
    }

    //POST ADMIN?
    @PostMapping("/isAdm")
    public Boolean ReceberLoginAdm(@RequestBody EmailRequest data) {
        String email = data.email();
        String role;
        Usuario usuario;
        try {
            usuario = usuarioServices.findByEmail(email);
            role = usuario.getRole();
            if(role.equals("ROLE_ADM")) return true;
            return false /* usuario existe, porem nao eh adm */;
        } catch (Exception e) {
            return false /* nao foi encontrado o email */;
        }
        
    }

        //POST MASTER?
    @PostMapping("/isMaster")
    public Boolean ReceberLoginMaster(@RequestBody EmailRequest data) {
        String email = data.email();
        String role;
        Usuario usuario;
        try {
            usuario = usuarioServices.findByEmail(email);
            role = usuario.getRole();
            if(role.equals("ROLE_MASTER")) return true;
            return false /* usuario existe, porem nao eh adm */;
        } catch (Exception e) {
            return false /* nao foi encontrado o email */;
        }
        
    }


    //POST PARA VER SE USUARIO EXISTE
    @PostMapping("/exists")
    public Boolean UsuarioExiste(@RequestBody EmailRequest data){
        String email = data.email();
        try{
            usuarioServices.findByEmail(email);
            return true;
        }catch(Exception e){
            return false;
        }
        
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
