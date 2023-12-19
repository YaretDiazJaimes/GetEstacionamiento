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

    public String getNumeroPlaca() {
        return numeroPlaca;
    }

    public void setNumeroPlaca(String numeroPlaca) {
        this.numeroPlaca = numeroPlaca;
    }

    @Column(name = "numero_placa")
    private String numeroPlaca;

    public LocalDateTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalDateTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    @Column(name = "hora_entrada")
    private LocalDateTime horaEntrada;
    public void setEntrada(LocalDateTime entrada) {
        this.entrada = entrada;
    }

    private LocalDateTime entrada;

    public LocalDateTime getSalida() {
        return horaSalida;
    }

    public void setSalida(LocalDateTime salida) {
        this.horaSalida = salida;
    }

    private LocalDateTime horaSalida;

    public long getTiempoEstacionado() {
        return tiempoEstacionado;
    }

    public void setTiempoEstacionado(long tiempoEstacionado) {
        this.tiempoEstacionado = tiempoEstacionado;
    }

    @Formula("(CASE WHEN hora_salida IS NOT NULL THEN TIMESTAMPDIFF(MINUTE, hora_entrada, hora_salida) ELSE 0 END)")
    private long tiempoEstacionado;


    public double calcularTiempoEstacionado() {
        return 0;
    }

    public String getResidenteNumeroPlaca() {
        return residenteNumeroPlaca;
    }

    private String residenteNumeroPlaca;
}



