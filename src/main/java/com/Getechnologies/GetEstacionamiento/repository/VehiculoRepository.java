package com.Getechnologies.GetEstacionamiento.repository;

import com.Getechnologies.GetEstacionamiento.dominio.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, String> {
    Vehiculo findByNumeroPlaca(String numeroPlaca);
}
