package com.sv.repositorio;

import com.sv.modelos.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfaceUser extends CrudRepository<Users, Integer> {
}
