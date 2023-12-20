package com.Getechnologies.GetEstacionamiento.dominio;

import jakarta.persistence.*;

@Entity
public class Residente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "residente_id")
    private Residente residente;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Residente getResidente() {
        return residente;
    }

    public void setResidente(Residente residente) {
        this.residente = residente;
    }

    public String getNumeroPlaca() {
        return null;
    }
}
