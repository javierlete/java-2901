package com.ipartek.formacion.amazonia.repositorios;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.ipartek.formacion.amazonia.entidades.Usuario;

@RepositoryRestResource(collectionResourceRel = "productos", path = "productos")
public interface UsuarioRepository extends CrudRepository<Usuario, Long>{
	Optional<Usuario> findByEmail(String email);
}
