package com.Getechnologies.GetEstacionamiento.service;



public interface EstacionamientoService {
    void registrarEntrada(String numeroPlaca);

    void registrarSalida(String numeroPlaca);

    double calcularImporte(String numeroPlaca);

    void darAltaVehiculoResidente(String placaResidente);

    void comenzarNuevoMes();

    void registrarVehiculoOficial(String numeroPlaca);

    boolean esResidente(String numeroPlaca);



}




