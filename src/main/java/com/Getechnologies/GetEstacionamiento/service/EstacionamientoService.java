package com.Getechnologies.GetEstacionamiento.service;

import org.springframework.web.bind.annotation.GetMapping;

public interface EstacionamientoService {
    void registrarEntrada(String numeroPlaca);

    void registrarSalida(String numeroPlaca);

    double calcularImporte(String numeroPlaca);



    }




