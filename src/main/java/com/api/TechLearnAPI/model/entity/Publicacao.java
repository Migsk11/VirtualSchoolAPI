package com.api.TechLearnAPI.model.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Publicacao")
public class Publicacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String titulo;

    @Column(length = 55)
    private String subtitulo;

    @Column(length = 255, nullable = false)
    private String descricao;


    private byte[] imagem_url; // Ajustar...

    @Column(nullable = false)
    private Date data_publicacao;

    @Column(length = 100, nullable = false)
    private String tipo_publicacao;

    @Column(length = 55, nullable = false)
    private String status_publicacao;


    //Getters And Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public byte[] getImagem_url() {
        return imagem_url;
    }

    public void setImagem_url(byte[] imagem_url) {
        this.imagem_url = imagem_url;
    }

    public Date getData_publicacao() {
        return data_publicacao;
    }

    public void setData_publicacao(Date data_publicacao) {
        this.data_publicacao = data_publicacao;
    }

    public String getTipo_publicacao() {
        return tipo_publicacao;
    }

    public void setTipo_publicacao(String tipo_publicacao) {
        this.tipo_publicacao = tipo_publicacao;
    }

    public String getStatus_publicacao() {
        return status_publicacao;
    }

    public void setStatus_publicacao(String status_publicacao) {
        this.status_publicacao = status_publicacao;
    }


}
