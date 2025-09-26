package com.ipartek.formacion.bibliotecas;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Consola {
	private static final Scanner SC = new Scanner(System.in);
	
	public static void pfl(String formato, Object... argumentos) {
		System.out.printf(formato + "\n", argumentos);
	}
	
	public static void pl(Object objeto) {
		System.out.println(objeto);
	}
	
	public static void p(Object objeto) {
		System.out.print(objeto);
	}
	
	public static String leerString(String mensaje) {
		p(mensaje + ": ");
		
		return SC.nextLine();
	}
	
	public static int leerInt(String mensaje) {
		do {
			try {
				return Integer.parseInt(leerString(mensaje));
			} catch (NumberFormatException e) {
				pl("El dato introducido no es un número válido");
			} 
		} while (true);
	}
	
	public static long leerLong(String mensaje) {
		do {
			try {
				return Long.parseLong(leerString(mensaje));
			} catch (NumberFormatException e) {
				pl("El dato introducido no es un número válido");
			} 
		} while (true);
	}
	
	public static LocalDateTime leerLocalDateTime(String mensaje) {
		do {
			try {
				return LocalDateTime.parse(leerString(mensaje));
			} catch (DateTimeParseException e) {
				pl("El dato introducido no es una fecha válida");
			} 
		} while (true);
	}
}
