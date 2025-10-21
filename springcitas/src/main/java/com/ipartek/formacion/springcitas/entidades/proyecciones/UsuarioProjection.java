package com.ipartek.formacion.springcitas.entidades.proyecciones;

import org.springframework.data.rest.core.config.Projection;

import com.ipartek.formacion.springcitas.entidades.Usuario;

@Projection(name = "usuarioConRol", types = Usuario.class)
public interface UsuarioProjection {
	Long getId();
	String getNombre();
	String getEmail();
	
	RolProjection getRol();
	
	interface RolProjection {
		Long getId();
		String getNombre();
	}
}
