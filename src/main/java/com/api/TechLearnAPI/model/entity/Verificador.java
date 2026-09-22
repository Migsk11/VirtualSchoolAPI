package com.api.TechLearnAPI.model.entity;

import java.time.LocalDateTime;
import java.util.UUID;

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
    private UUID uuid;

    private Boolean status_uuid;

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

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Boolean getStatus_uuid() {
        return status_uuid;
    }

    public void setStatus_uuid(Boolean status_uuid) {
        this.status_uuid = status_uuid;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
