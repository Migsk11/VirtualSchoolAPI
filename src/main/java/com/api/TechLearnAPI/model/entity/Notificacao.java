package com.api.TechLearnAPI.model.entity;

import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name = "Notificacao")
public class Notificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String titulo;

    @Column(length = 255)
    private String mensagem;

    @Column(nullable = false)
    private Date data_envio;

    @Column(length = 55, nullable = false)
    private String status_notificacao;

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

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Date getData_envio() {
        return data_envio;
    }

    public void setData_envio(Date data_envio) {
        this.data_envio = data_envio;
    }

    public String getStatus_notificacao() {
        return status_notificacao;
    }

    public void setStatus_notificacao(String status_notificacao) {
        this.status_notificacao = status_notificacao;
    }


}
