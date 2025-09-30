package com.ipartek.formacion.bibliotecas;

import java.util.Optional;

import com.ipartek.formacion.citas.entidades.Cita;

public interface Dao<T> {
	Iterable<T> obtenerTodos();
	Optional<Cita> obtenerPorId(Long id);
	
	T insertar(T cita);
	T modificar(T cita);
	void borrar(Long id);
}
