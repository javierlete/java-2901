package com.ipartek.formacion.springcitas.servicios;

import java.util.Collection;
import java.util.Optional;

import com.ipartek.formacion.springcitas.entidades.Cita;
import com.ipartek.formacion.springcitas.entidades.Usuario;

public interface AdminService extends UsuarioService {
	Cita altaCita(Cita cita);
	Cita modificarCita(Cita cita);
	void bajaCita(Long id);
	
	Collection<Usuario> listadoUsuarios();
	Optional<Usuario> usuarioPorId(Long id);
	
	Usuario altaUsuario(Usuario usuario);
	Usuario modificacionUsuario(Usuario usuario);
	void bajaUsuario(Long id);
}
