package com.Getechnologies.GetEstacionamiento.repository;

import com.Getechnologies.GetEstacionamiento.dominio.Estancia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstanciaRepository extends JpaRepository<Estancia, Long> {

    @Modifying
    @Query("DELETE FROM Estancia WHERE cocheOficial = true")
    void eliminarEstanciasCochesOficiales();

    @Modifying
    @Query("UPDATE Estancia e SET e.tiempoEstacionado = 0 WHERE e.cocheOficial = false")
    void reiniciarTiempoEstacionadoResidentes();
    Estancia findByNumeroPlacaAndHoraSalidaIsNull(String numeroPlaca);

    Estancia findByNumeroPlacaAndHoraSalidaIsNotNull(String numeroPlaca);

    List<Estancia> findByResidenteNumeroPlaca(String residenteNumeroPlaca);

    void deleteByCocheOficial(boolean b);
    List<Estancia> findByResidenteVerdad(boolean residenteVerdad);


}

