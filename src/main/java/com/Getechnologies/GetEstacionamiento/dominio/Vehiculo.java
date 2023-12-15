package com.Getechnologies.GetEstacionamiento.dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class Vehiculo {
    public String getNumeroPlaca() {
        return numeroPlaca;
    }

    public void setNumeroPlaca(String numeroPlaca) {
        this.numeroPlaca = numeroPlaca;
    }

    @Id
    private String numeroPlaca;

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    private String tipoVehiculo;

}