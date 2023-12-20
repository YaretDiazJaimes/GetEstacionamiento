package com.Getechnologies.GetEstacionamiento.dominio;

import jakarta.persistence.*;

@Entity
public class Vehiculo {

    @Id
    private String numeroPlaca;

    private String tipoVehiculo;


    public String getNumeroPlaca() {
        return numeroPlaca;
    }

    public void setNumeroPlaca(String numeroPlaca) {
        this.numeroPlaca = numeroPlaca;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }
}
