package com.Getechnologies.GetEstacionamiento.repository;

import com.Getechnologies.GetEstacionamiento.dominio.Estancia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstanciaRepository extends JpaRepository<Estancia, Long> {


    Estancia findByNumeroPlacaAndHoraSalidaIsNull(String numeroPlaca);

    Estancia findByNumeroPlacaAndHoraSalidaIsNotNull(String numeroPlaca);
}

