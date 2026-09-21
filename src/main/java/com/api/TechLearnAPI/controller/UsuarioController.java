package com.api.TechLearnAPI.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log = LoggerFactory.getLogger(UsuarioServices.class);

    


    //GET QUANT USUARIOS
    @GetMapping("/quantiaUsuarios")
    public ResponseEntity<Long> QuantiaUsuarios(){
        return ResponseEntity.ok(usuarioServices.ContarUsuarios());
    }


    public record LoginRequest(String email, String senha) {}
    
    //POST LOGIN
    @PostMapping("/login")
    public Boolean ReceberLogin(@RequestBody LoginRequest data) {
        String email = data.email();
        String senha = data.senha();
        try {
            Usuario usuario = usuarioServices.findByEmail(email);
            boolean findPassword = usuario.getSenha().equals(senha);

            return findPassword;
        } catch (Exception e) {
            log.error("Houve um erro no fluxo: Login");
            return false /* algum erro no processo */;
        }
        
    }

    //POST ADMIN?
    @PostMapping("/isAdm")
    public Boolean ReceberLoginAdm(@RequestBody LoginRequest data) {
        String email = data.email();
        String role;
        Usuario usuario;
        try {
            usuario = usuarioServices.findByEmail(email);
            role = usuario.getRole();
            
            return role.equals("ROLE_ADM");
        } catch (Exception e) {
            log.error("Houve um erro no fluxo: Autenticação do Nivel de Acesso");
            return false;
        }
        
    }

    //POST MASTER?
    @PostMapping("/isMaster")
    public Boolean ReceberLoginMaster(@RequestBody LoginRequest data) {
        String email = data.email();
        String role;
        Usuario usuario;
        try {
            usuario = usuarioServices.findByEmail(email);
            role = usuario.getRole();

            return role.equals("ROLE_MASTER");
        } catch (Exception e) {
            log.error("Houve um erro no fluxo: Autenticação do Nivel de Acesso");
            return false;
        }
        
    }


    //POST PARA VER SE USUARIO EXISTE
    @PostMapping("/exists")
    public Boolean UsuarioExiste(@RequestBody LoginRequest data){
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

    // NEW - GET USER BY EMAIL (returns logged user data by email)
    @GetMapping("/me/{email}")
    public ResponseEntity<Object> obterUsuarioLogadoPorEmail(@PathVariable String email) {
        try {
            Usuario usuario = usuarioServices.findByEmail(email);

            Map<String, Object> resposta = Map.of(
                "id", usuario.getId(),
                "nome", usuario.getNome(),
                "email", usuario.getEmail(),
                "role", usuario.getRole()
            );

            return ResponseEntity.ok(resposta);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(
                Map.of(
                    "status", 404,
                    "retorno", "Not Found",
                    "message", "Usuário não encontrado"
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
