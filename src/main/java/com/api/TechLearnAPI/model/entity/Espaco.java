package com.api.TechLearnAPI.model.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "Espaco")
public class Espaco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String tipo_espaco;

    @Column(length = 55, nullable = false)
    private String status_espaco;

    @Column(length = 100, nullable = false)
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
