package com.Getechnologies.GetEstacionamiento.repository;

import com.Getechnologies.GetEstacionamiento.dominio.Residente;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ResidenteRepository {
    List<Residente> findAll();
}
