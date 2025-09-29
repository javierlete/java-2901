package com.ipartek.formacion.citas.pruebas;

import java.time.LocalDateTime;

import com.ipartek.formacion.citas.accesodatos.DaoCita;
import com.ipartek.formacion.citas.accesodatos.DaoCitaJdbc;
import com.ipartek.formacion.citas.entidades.Cita;

public class DaoCitaPrueba {
	public static void main(String[] args) {
		DaoCita dao = new DaoCitaJdbc();

		dao.insertar(
				new Cita("Antesdeayer", LocalDateTime.of(2025, 9, 24, 8, 15), LocalDateTime.of(2025, 9, 24, 13, 45)));
		dao.insertar(new Cita("Ayer", LocalDateTime.of(2025, 9, 25, 8, 15), LocalDateTime.of(2025, 9, 25, 13, 45)));
		dao.insertar(new Cita("Hoy", LocalDateTime.of(2025, 9, 26, 8, 15), LocalDateTime.of(2025, 9, 26, 13, 45)));

		dao.obtenerTodos().forEach(System.out::println);

		System.out.println();

		dao.modificar(new Cita(2L, "MODIFICADA", LocalDateTime.of(2025, 9, 25, 8, 15),
				LocalDateTime.of(2025, 9, 25, 13, 45)));

		dao.obtenerPorId(2L).ifPresentOrElse(System.out::println, () -> System.out.println("No la he encontrado"));
		dao.obtenerPorId(4L).ifPresentOrElse(System.out::println, () -> System.out.println("No la he encontrado"));

		System.out.println();

		dao.buscarPorTexto("ayer").forEach(System.out::println);

		System.out.println();

		dao.borrar(1L);

		dao.obtenerTodos().forEach(System.out::println);

	}
}
