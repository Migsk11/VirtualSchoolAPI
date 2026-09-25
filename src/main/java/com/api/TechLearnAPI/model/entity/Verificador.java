package com.api.TechLearnAPI.model.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;







@Entity 
@Table(name="Verificador")
public class Verificador {

    @Id 
    private Long keyValidation;

    @OneToOne
    @JoinColumn(name="usuario_id")
    private Usuario usuario;

    @Column(name = "data_expiracao")
    private LocalDateTime dateExpire;


    





    

    public LocalDateTime getDateExpire() {
        return dateExpire;
    }

    public void setDateExpire(LocalDateTime dateExpire) {
        this.dateExpire = dateExpire;
    }

    public Long getKeyValidation() {
        return keyValidation;
    }

    public void setKeyValidation(Long keyValidation) {
        this.keyValidation = keyValidation;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
