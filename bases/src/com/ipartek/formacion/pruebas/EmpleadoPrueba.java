package com.ipartek.formacion.pruebas;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.ipartek.formacion.pojos.Empleado;
import com.ipartek.formacion.pojos.EmpleadoIndefinido;
import com.ipartek.formacion.pojos.EmpleadoPorHoras;
import com.ipartek.formacion.pojos.Local;
import com.ipartek.formacion.pojos.Persona;

public class EmpleadoPrueba {
	public static void main(String[] args) {
		Empleado empleado = new EmpleadoIndefinido("Pepe", LocalDate.of(2001, 2, 3), 14, new BigDecimal("23456"));

		System.out.println(empleado);

		empleado.setId(1L);
		empleado.setNombre("Javier");
		empleado.setFechaNacimiento(LocalDate.of(2000, 1, 2));

		System.out.println(empleado);

		Persona persona = empleado;

		System.out.println(persona.getNombre());
//		System.out.println(persona.getSueldoMensual());

		if (persona instanceof Empleado) {
			Empleado empleado2 = (Empleado) persona;

			System.out.println(empleado2.getSueldoMensual());
		} else {
			System.out.println("No es un empleado");
		}

		Persona persona2 = new Persona();

		if (persona2 instanceof Empleado empleado3) {
//			Empleado empleado3 = (Empleado) persona2;

			System.out.println(empleado3.getSueldoMensual());
		} else {
			System.out.println("No es un empleado");
		}
		
		Local local = new Local("Bilbao", empleado);
		
		local.entrar(empleado);
		local.entrar(new EmpleadoPorHoras("Pepe", LocalDate.of(2003, 2, 1), 80, new BigDecimal("15")));
		
		persona2.setNombre("Juan");
		
		local.entrar(persona2);
		
		for(Persona p: local.getVisitas()) {
			System.out.println(p);
			if(p instanceof Empleado e) {
				System.out.println(e.getSueldoMensual());
			}
		}
		
		Empleado e1 = new EmpleadoIndefinido("Javier", LocalDate.of(2000, 1, 2), 14, new BigDecimal("12345"));
		Empleado e2 = new EmpleadoIndefinido("Javier", LocalDate.of(2000, 1, 2), 14, new BigDecimal("12345"));
		
		System.out.println(e1);
		System.out.println(e2);
		
		System.out.println(e1 == e2); // ¿Son el mismo objeto?
		System.out.println(e1.equals(e2)); // ¿Son iguales?
	}
}
