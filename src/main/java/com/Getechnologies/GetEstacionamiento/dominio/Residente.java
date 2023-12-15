package com.Getechnologies.GetEstacionamiento.dominio;


import javax.persistence.Entity;
import javax.persistence.Id;

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

