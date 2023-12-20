package com.Getechnologies.GetEstacionamiento.service;

import com.Getechnologies.GetEstacionamiento.dominio.Estancia;
import com.Getechnologies.GetEstacionamiento.dominio.Vehiculo;
import com.Getechnologies.GetEstacionamiento.repository.EstanciaRepository;
import com.Getechnologies.GetEstacionamiento.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
@Service
public class InformePagosService {
    @Autowired
    private EstanciaRepository estanciaRepository;
    @Autowired
    private EstacionamientoServiceImpl estacionamientoService;
    @Autowired
    private VehiculoRepository vehiculoRepository;

    private static final double TARIFA_RESIDENTES = 0.05;
    private static final double TARIFA_NO_RESIDENTES = 0.5;



    public void generarInforme(String nombreArchivo) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo))) {
            List<Estancia> estancias = estanciaRepository.findAll();

            for (Estancia estancia : estancias) {
                long tiempoEstacionado = calcularTiempoEstacionado(estancia);
                double montoAPagar = calcularMontoAPagar(estancia);

                writer.printf("%s\t%d\t%.2f%n", estancia.getNumeroPlaca(), tiempoEstacionado, montoAPagar);
            }

            System.out.println("Informe generado exitosamente.");
        } catch (IOException e) {
            System.err.println("Error al generar el informe: " + e.getMessage());
        }
    }

    private double calcularMontoAPagar(Estancia estancia) {
        if (esOficial(estancia.getNumeroPlaca())) {
            return 0.0;
        } else {
            double tiempoEstacionado = calcularTiempoEstacionado(estancia);

            if (esResidente(estancia.getNumeroPlaca())) {
                return tiempoEstacionado * TARIFA_RESIDENTES;
            } else {
                return tiempoEstacionado * TARIFA_NO_RESIDENTES;
            }
        }
    }

    private long calcularTiempoEstacionado(Estancia estancia) {
        return estancia.getTiempoEstacionado();
    }

    private boolean esOficial(String numeroPlaca) {
        Vehiculo vehiculo = vehiculoRepository.findByNumeroPlaca(numeroPlaca);
        return vehiculo != null && "oficial".equalsIgnoreCase(vehiculo.getTipoVehiculo());
    }

    private boolean esResidente(String numeroPlaca) {
        return estacionamientoService.esResidente(numeroPlaca);
    }

}
