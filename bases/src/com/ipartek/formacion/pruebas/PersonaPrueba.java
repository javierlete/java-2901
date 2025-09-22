package com.ipartek.formacion.pruebas;

import java.time.LocalDate;

import com.ipartek.formacion.pojos.Persona;

public class PersonaPrueba {
	public static void main(String[] args) {
		var persona = new Persona();

		System.out.println(persona.getId());
		System.out.println(persona.getNombre());
		System.out.println(persona.getFechaNacimiento());

		persona.setId(1L);
		persona.setNombre("   Javier       ");
		persona.setFechaNacimiento(LocalDate.of(2025, 9, 22));

		System.out.println(persona.toString());
		
		var persona2 = new Persona(2L, "Pepe", LocalDate.of(2000, 1, 2));
		
		System.out.println(persona2);
		
		var persona3 = new Persona(persona2);
		
		persona3.setId(3L);
		
		System.out.println(persona2);
		System.out.println(persona3);
	}
}
