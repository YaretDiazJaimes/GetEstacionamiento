package com.Getechnologies.GetEstacionamiento.dominio;

import jakarta.persistence.*;
import org.hibernate.annotations.Formula;

import java.time.LocalDateTime;

@Entity
@Table(name = "estancias")
public class Estancia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_placa")
    private String numeroPlaca;

    @Column(name = "hora_entrada")
    private LocalDateTime horaEntrada;

    @Column(name = "hora_salida")
    private LocalDateTime horaSalida;

    @Formula("(CASE WHEN hora_salida IS NOT NULL THEN TIMESTAMPDIFF(MINUTE, hora_entrada, hora_salida) ELSE 0 END)")
    private long tiempoEstacionado;

    @Column(name = "residente_verdad")
    private boolean residenteVerdad;

    private String residenteNumeroPlaca;

    private boolean cocheOficial;

    private LocalDateTime entrada;

    public Estancia() {

    }

    public Estancia(String numeroPlaca, LocalDateTime horaEntrada) {
        this.numeroPlaca = numeroPlaca;
        this.horaEntrada = horaEntrada;
    }



    public String getNumeroPlaca() {
        return numeroPlaca;
    }

    public void setNumeroPlaca(String numeroPlaca) {
        this.numeroPlaca = numeroPlaca;
    }

    public LocalDateTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalDateTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public LocalDateTime getSalida() {
        return horaSalida;
    }

    public void setSalida(LocalDateTime salida) {
        this.horaSalida = salida;
    }

    public long getTiempoEstacionado() {
        return tiempoEstacionado;
    }

    public void setTiempoEstacionado(long tiempoEstacionado) {
        this.tiempoEstacionado = tiempoEstacionado;
    }

    public double calcularTiempoEstacionado() {
        return 0;
    }

    public String getResidenteNumeroPlaca() {
        return residenteNumeroPlaca;
    }

    public boolean isCocheOficial() {
        return cocheOficial;
    }

    public void setCocheOficial(boolean cocheOficial) {
        this.cocheOficial = cocheOficial;
    }

    public void getPlaca() {

    }

    public boolean isResidenteVerdad() {
        return residenteVerdad;
    }

    public void setResidenteVerdad(boolean residenteVerdad) {
        this.residenteVerdad = residenteVerdad;
    }
}
