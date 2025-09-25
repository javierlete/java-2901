package com.ipartek.formacion.pruebas;

import com.ipartek.formacion.pojos.Impresora;
import com.ipartek.formacion.pojos.Local;
import com.ipartek.formacion.pojos.Persona;

public class ImprimiblePrueba {
	public static void main(String[] args) {
		Persona persona = new Persona("Javier");
		Local local = new Local("Bilbao", persona);
		
		Impresora impresora = new Impresora();
		
		impresora.agregarImprimible(local);
		impresora.agregarImprimible(persona);
		
		impresora.imprimir();
	}
}
