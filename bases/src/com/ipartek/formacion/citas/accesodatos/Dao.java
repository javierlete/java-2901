package com.ipartek.formacion.citas.accesodatos;

import java.util.Optional;

import com.ipartek.formacion.citas.entidades.Cita;

interface Dao<T> {
	Iterable<T> obtenerTodos();
	Optional<Cita> obtenerPorId(Long id);
	
	T insertar(T cita);
	T modificar(T cita);
	void borrar(Long id);
}
