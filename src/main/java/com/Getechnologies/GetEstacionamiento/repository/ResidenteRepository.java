package com.Getechnologies.GetEstacionamiento.repository;

import com.Getechnologies.GetEstacionamiento.dominio.Residente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResidenteRepository extends JpaRepository<Residente, Long> {
    List<Residente> findAll();
}
