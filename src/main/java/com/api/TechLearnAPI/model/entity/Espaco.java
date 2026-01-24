package com.api.TechLearnAPI.model.entity;

public class Espaco {

    private Long id;
    private String tipo_espaco;
    private String status_espaco;
    private String nome;


    //Getters And Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo_espaco() {
        return tipo_espaco;
    }

    public void setTipo_espaco(String tipo_espaco) {
        this.tipo_espaco = tipo_espaco;
    }

    public String getStatus_espaco() {
        return status_espaco;
    }

    public void setStatus_espaco(String status_espaco) {
        this.status_espaco = status_espaco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


}
