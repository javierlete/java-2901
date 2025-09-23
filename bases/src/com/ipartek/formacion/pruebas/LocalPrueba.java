package com.ipartek.formacion.pruebas;

import java.time.LocalDate;

import com.ipartek.formacion.pojos.Local;
import com.ipartek.formacion.pojos.Persona;

public class LocalPrueba {
	public static void main(String[] args) {
		Local local = new Local("Bilbao", new Persona("Javier"), Local.PARA_ADULTOS);
		
		System.out.println(local);
		
		Persona juan = new Persona("Juan", LocalDate.of(2000, 1, 2));
		Persona pepe = new Persona("Pepe", LocalDate.of(2025, 1, 2));
		Persona pedro = new Persona("Pedro", LocalDate.of(2000, 1, 2));
		
		System.out.println(pedro);
		
		local.entrar(juan);
		local.entrar(pepe);
		local.entrar(pedro);
		
		System.out.println(local.getVisitas());

		local.salir(juan);
		
		System.out.println(local.getVisitas());
	}
}
