package com.Getechnologies.GetEstacionamiento.dominio;


import jakarta.persistence.*;

@Entity
public class Residente {

    @Id
    private Long id;

    public String getNumeroPlaca() {
        return null;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

