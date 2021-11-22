package com.sv.repositorio;

import com.sv.modelos.Mascotas;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfaceMascota extends CrudRepository<Mascotas, Integer> {
}
