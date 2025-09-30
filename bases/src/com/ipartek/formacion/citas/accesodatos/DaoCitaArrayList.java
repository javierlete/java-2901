package com.ipartek.formacion.citas.accesodatos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import com.ipartek.formacion.citas.entidades.Cita;

public class DaoCitaArrayList implements DaoCita {
	private final ArrayList<Cita> citas = new ArrayList<>();

	public DaoCitaArrayList() {
		insertar(new Cita("Antesdeayer", LocalDateTime.of(2025,  9, 24, 8, 15), LocalDateTime.of(2025,  9, 24, 13, 45)));
		insertar(new Cita("Ayer", LocalDateTime.of(2025,  9, 25, 8, 15), LocalDateTime.of(2025,  9, 25, 13, 45)));
		insertar(new Cita("Hoy", LocalDateTime.of(2025,  9, 26, 8, 15), LocalDateTime.of(2025,  9, 26, 13, 45)));
	}
	
	@Override
	public Iterable<Cita> obtenerTodos() {
		return citas;
	}

	@Override
	public Optional<Cita> obtenerPorId(Long id) {
		return citas.stream().filter(c -> c.getId() == id).findFirst();
	}

	@Override
	public Cita insertar(Cita cita) {
		if (cita.getId() != null) {
			throw new AccesoDatosException("No se admite añadir citas con id específico");
		}

		Long id = citas.stream().map(c -> c.getId()).max(Long::compareTo).orElse(0L) + 1L;

		cita.setId(id);

		citas.add(cita);

		return cita;
	}

	@Override
	public Cita modificar(Cita cita) {
		return citas.set(buscarIndice(cita.getId()), cita);
	}

	@Override
	public void borrar(Long id) {
		citas.remove(buscarIndice(id));
	}

	@Override
	public Iterable<Cita> buscarPorTexto(String texto) {
		return citas.stream().filter(c -> c.getTexto().toLowerCase().contains(texto.toLowerCase())).toList();
	}

	private int buscarIndice(Long id) {
		if (id == null) {
			throw new AccesoDatosException("No se puede realizar la operación sobre una cita sin saber el id");
		}
	
		for (int i = 0; i < citas.size(); i++) {
			if (id == citas.get(i).getId()) {
				return i;
			}
		}
	
		throw new AccesoDatosException("No se ha encontrado la cita");
	}

}
