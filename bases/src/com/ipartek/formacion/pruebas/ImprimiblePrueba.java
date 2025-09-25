package com.ipartek.formacion.pruebas;

import com.ipartek.formacion.pojos.Impresora;
import com.ipartek.formacion.pojos.Imprimible;
import com.ipartek.formacion.pojos.Local;
import com.ipartek.formacion.pojos.Persona;

public class ImprimiblePrueba {
	private static final int NUMERO_CARACTERES = 50;

	public static void main(String[] args) {
		Persona persona = new Persona("Javier");
		Local local = new Local("Bilbao", persona);
		
		Impresora impresora = new Impresora();
		
		impresora.agregarImprimible(new Separador(NUMERO_CARACTERES));
		impresora.agregarImprimible(local);
		impresora.agregarImprimible(new SeparadorClaseInterna());
		impresora.agregarImprimible(persona);
		impresora.agregarImprimible(new Imprimible() {
			@Override
			public String imprimir() {
				return "+".repeat(NUMERO_CARACTERES);
			}
		});
		impresora.agregarImprimible(() -> "Hecho por Javier");
		impresora.agregarImprimible(() -> "*".repeat(NUMERO_CARACTERES));
		
		impresora.imprimir();
	}
	
	static class SeparadorClaseInterna implements Imprimible {
		@Override
		public String imprimir() {
			return "=".repeat(NUMERO_CARACTERES);
		}
	}
}

class Separador implements Imprimible {
	private Integer numeroCaracteres;

	public Separador(Integer numeroCaracteres) {
		this.numeroCaracteres = numeroCaracteres;
	}

	@Override
	public String imprimir() {
		return "-".repeat(numeroCaracteres);
	}
}