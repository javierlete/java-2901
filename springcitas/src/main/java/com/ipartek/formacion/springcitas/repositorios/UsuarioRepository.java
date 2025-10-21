package com.ipartek.formacion.springcitas.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.ipartek.formacion.springcitas.entidades.Usuario;

@RepositoryRestResource(collectionResourceRel = "usuarios", path = "usuarios")
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
	Optional<Usuario> findByEmail(String email);

	@EntityGraph(attributePaths = "rol")
	Iterable<Usuario> findAll();
	
//	@Query("select new com.ipartek.formacion.springcitas.dtos.UsuarioRolDto(u.id, u.nombre, u.email, r.id, r.nombre) from Usuario u left join u.rol r")
//	List<UsuarioRolDto> usuariosConRoles();
	
//	@Query("from Usuario u left join fetch u.rol r")
//	Iterable<Usuario> usuariosConRoles();
}
