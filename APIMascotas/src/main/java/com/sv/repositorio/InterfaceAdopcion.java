package com.sv.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sv.modelos.Adopcion;

@Repository
public interface InterfaceAdopcion extends CrudRepository<Adopcion, Integer>{

}
