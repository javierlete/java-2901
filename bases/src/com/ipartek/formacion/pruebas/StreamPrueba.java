package com.ipartek.formacion.pruebas;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ipartek.formacion.pojos.Empleado;
import com.ipartek.formacion.pojos.EmpleadoIndefinido;
import com.ipartek.formacion.pojos.EmpleadoPorHoras;

public class StreamPrueba {
	public static void main(String[] args) {
		List<Empleado> personal = new ArrayList<>();

		personal.add(new EmpleadoIndefinido("Javier", LocalDate.of(2000, 1, 2), 14, new BigDecimal(23456)));
		personal.add(new EmpleadoIndefinido("Pepe", LocalDate.of(2001, 1, 2), 14, new BigDecimal(33456)));
		personal.add(new EmpleadoPorHoras("Juan", LocalDate.of(2002, 1, 2), 80, new BigDecimal(23)));
		personal.add(new EmpleadoPorHoras("Pedro", LocalDate.of(2003, 1, 2), 70, new BigDecimal(34)));

		for (var e : personal) {
			System.out.println(e);
		}

		System.out.println();

		personal.forEach(e -> System.out.println(e));

		System.out.println();

		personal.forEach(System.out::println);

		System.out.println();

		personal.stream().map(e -> e.getSueldoMensual()).sorted().forEach(System.out::println);

		System.out.println();

		personal.stream().filter(e -> e.getSueldoMensual().compareTo(new BigDecimal(2000)) >= 0)
				.forEach(System.out::println);

		System.out.println();

		BigDecimal totalSueldosMensuales = personal.stream().map(e -> e.getSueldoMensual()).reduce(BigDecimal.ZERO,
				(sueldoMensual, acumulado) -> sueldoMensual.add(acumulado));

		System.out.println(totalSueldosMensuales);

		System.out.println();

		personal.stream().map(e -> e.getSueldoMensual())
				.reduce((sueldoMensual, acumulado) -> sueldoMensual.add(acumulado)).ifPresent(System.out::println);

		System.out.println();

		personal.stream().sorted((e1, e2) -> e1.getSueldoMensual().compareTo(e2.getSueldoMensual()))
				.forEach(e -> System.out.printf("%s: %s\n", e, e.getSueldoMensual()));
	}
}
