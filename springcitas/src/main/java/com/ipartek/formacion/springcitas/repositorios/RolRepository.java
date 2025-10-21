package com.ipartek.formacion.springcitas.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.ipartek.formacion.springcitas.entidades.Rol;

@RepositoryRestResource(collectionResourceRel = "roles", path = "roles")
public interface RolRepository extends CrudRepository<Rol, Long> {

}
