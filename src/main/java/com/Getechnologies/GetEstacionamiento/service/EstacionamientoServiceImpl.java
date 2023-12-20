package com.Getechnologies.GetEstacionamiento.service;

import com.Getechnologies.GetEstacionamiento.dominio.Estancia;
import com.Getechnologies.GetEstacionamiento.dominio.Residente;
import com.Getechnologies.GetEstacionamiento.dominio.Vehiculo;
import com.Getechnologies.GetEstacionamiento.repository.EstanciaRepository;
import com.Getechnologies.GetEstacionamiento.repository.ResidenteRepository;
import com.Getechnologies.GetEstacionamiento.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EstacionamientoServiceImpl implements EstacionamientoService {

    @Autowired
    private EstanciaRepository estanciaRepository;

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Autowired
    private ResidenteRepository residenteRepository;

    private static final double TARIFA_RESIDENTES = 0.05;
    private static final double TARIFA_NO_RESIDENTES = 0.5;

    @Override
    public void registrarEntrada(String numeroPlaca) {
        if (esOficial(numeroPlaca)) {
            registrarVehiculoOficial(numeroPlaca);
        }

        if (esResidente(numeroPlaca)) {
            registrarResidente(numeroPlaca);
        }
    }

    @Override
    @Transactional
    public void registrarVehiculoOficial(String numeroPlaca) {
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setNumeroPlaca(numeroPlaca);
        vehiculo.setTipoVehiculo("oficial");
        vehiculoRepository.save(vehiculo);
    }

    private void registrarResidente(String numeroPlaca) {
        Residente residente = new Residente();
        residente.setNumeroPlaca(numeroPlaca);
        residenteRepository.save(residente);
    }


    @Override
    @Transactional
    public void registrarSalida(String numeroPlaca) {
        LocalDateTime horaSalida = LocalDateTime.now();
        Estancia estancia = estanciaRepository.findByNumeroPlacaAndHoraSalidaIsNull(numeroPlaca);

        if (estancia != null) {
            estancia.setSalida(horaSalida);
            Duration tiempoEstacionado = Duration.between(estancia.getHoraEntrada(), horaSalida);
            estancia.setTiempoEstacionado(tiempoEstacionado.toMinutes());
            estanciaRepository.save(estancia);
        } else {
            throw new RuntimeException("No se encontró una estancia para la placa " + numeroPlaca);
        }
    }

    @Override
    @Transactional
    public double calcularImporte(String numeroPlaca) {
        Estancia estancia = obtenerEstancia(numeroPlaca);

        if (estancia != null) {
            if (esOficial(numeroPlaca)) {
                return 0.0;
            } else {
                double tiempoEstacionado = estancia.calcularTiempoEstacionado();
                if (esResidente(numeroPlaca)) {
                    return tiempoEstacionado * TARIFA_RESIDENTES;
                } else {
                    return tiempoEstacionado * TARIFA_NO_RESIDENTES;
                }
            }
        } else {
            throw new RuntimeException("No se encontró una estancia para la placa " + numeroPlaca);
        }
    }

    @Override
    @Transactional
    public void darAltaVehiculoResidente(String placaResidente) {

    }

    private Estancia obtenerEstancia(String numeroPlaca) {
        return estanciaRepository.findByNumeroPlacaAndHoraSalidaIsNotNull(numeroPlaca);
    }

    private boolean esOficial(String numeroPlaca) {
        Vehiculo vehiculo = vehiculoRepository.findByNumeroPlaca(numeroPlaca);
        return vehiculo != null && "oficial".equalsIgnoreCase(vehiculo.getTipoVehiculo());
    }

    public boolean esResidente(String numeroPlaca) {
        List<Residente> residentes = residenteRepository.findAll();
        return residentes.stream().anyMatch(residente -> residente.getNumeroPlaca().equalsIgnoreCase(numeroPlaca));
    }

    private double obtenerTiempoEstacionado(String numeroPlaca) {
        Optional<Estancia> optionalEstancia = Optional.ofNullable(estanciaRepository.findByNumeroPlacaAndHoraSalidaIsNull(numeroPlaca));

        if (optionalEstancia.isPresent()) {
            Estancia estancia = optionalEstancia.get();
            LocalDateTime horaEntrada = estancia.getHoraEntrada();
            LocalDateTime horaActual = LocalDateTime.now();
            Duration duracionEstacionado = Duration.between(horaEntrada, horaActual);
            return duracionEstacionado.toMinutes();
        } else {
            throw new RuntimeException("No se encontró una estancia activa para la placa " + numeroPlaca);
        }
    }

    @Override
    @Transactional
    public void comenzarNuevoMes() {
        eliminarEstanciasCochesOficiales();
        reiniciarTiempoEstacionadoResidentes();
    }

    @Transactional
    public void eliminarEstanciasCochesOficiales() {
        estanciaRepository.deleteByCocheOficial(true);
    }

    @Transactional
    public void reiniciarTiempoEstacionadoResidentes() {
        estanciaRepository.reiniciarTiempoEstacionadoResidentes();
    }
}

