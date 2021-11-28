package com.sv.repositorio;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sv.modelos.Adopcion;
import com.sv.modelos.Users;

@Repository
public interface InterfaceAdopcion extends CrudRepository<Adopcion, Integer>{

	List<Adopcion> findByIdUsuarioAdopta(Users idUsuarioAdopta);
	
}
