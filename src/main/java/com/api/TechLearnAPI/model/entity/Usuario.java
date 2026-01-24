package com.api.TechLearnAPI.model.entity;


public class Usuario {

    private Long id;
    private String nome;
    private String email;
    private String senha;
    private String tipo_usuario;
    private String status_cliente;


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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(String tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }

    public String getStatus_cliente() {
        return status_cliente;
    }

    public void setStatus_cliente(String status_cliente) {
        this.status_cliente = status_cliente;
    }

}
