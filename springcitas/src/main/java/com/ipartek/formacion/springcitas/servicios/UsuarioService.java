package com.ipartek.formacion.springcitas.servicios;

import java.util.Optional;

import com.ipartek.formacion.springcitas.entidades.Cita;

public interface UsuarioService {
	Iterable<Cita> listadoCitas();
	Optional<Cita> detalleCita(Long id);
}
