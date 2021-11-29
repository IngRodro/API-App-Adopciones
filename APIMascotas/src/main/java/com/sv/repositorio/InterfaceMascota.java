package com.sv.repositorio;

import com.sv.modelos.Mascotas;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfaceMascota extends CrudRepository<Mascotas, Integer> {
	List<Mascotas> findByIdmascota(Integer idmascota);
}
