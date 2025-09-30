package com.ipartek.formacion.citas.accesodatos;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.TreeMap;

import com.ipartek.formacion.citas.entidades.Cita;

class DaoCitaTreeMap implements DaoCita {
	private TreeMap<Long, Cita> citas = new TreeMap<>();

	public DaoCitaTreeMap() {
		insertar(new Cita("Miércoles", LocalDateTime.of(2025,  9, 24, 8, 15), LocalDateTime.of(2025,  9, 24, 13, 45)));
		insertar(new Cita("Jueves", LocalDateTime.of(2025,  9, 25, 8, 15), LocalDateTime.of(2025,  9, 25, 13, 45)));
		insertar(new Cita("Viernes", LocalDateTime.of(2025,  9, 26, 8, 15), LocalDateTime.of(2025,  9, 26, 13, 45)));
	}
	
	@Override
	public Iterable<Cita> obtenerTodos() {
		return citas.values();
	}

	@Override
	public Optional<Cita> obtenerPorId(Long id) {
		return Optional.of(citas.get(id));
	}

	@Override
	public Cita insertar(Cita cita) {
		Long id = citas.size() == 0 ? 1L: citas.lastKey() + 1L;
		
		cita.setId(id);
		
		citas.put(id, cita);
		
		return cita;
	}

	@Override
	public Cita modificar(Cita cita) {
		return citas.put(cita.getId(), cita);
	}

	@Override
	public void borrar(Long id) {
		citas.remove(id);
	}

	@Override
	public Iterable<Cita> buscarPorTexto(String texto) {
		return citas.values().stream().filter(c -> c.getTexto().toLowerCase().contains(texto.toLowerCase())).toList();
	}
}
