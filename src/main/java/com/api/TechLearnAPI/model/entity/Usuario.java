package com.api.TechLearnAPI.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;



@Entity
@Table(name = "Usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String nome;

    @Email(message="Email invalido...")
    @NotBlank(message="Email obrigatorio...")
    @Column(length = 155, nullable = false)
    private String email;

    @NotBlank(message="Senha obrigatoria...")
    @Column(name="user_password", length = 255, nullable = false)
    private String password;

    @Column(length = 55, nullable = false)
    private String roleUsuario;


    //Getters And Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleUsuario() {
        return roleUsuario;
    }

    public void setRoleUsuario(String roleUsuario) {
        this.roleUsuario = roleUsuario;
    }

}
