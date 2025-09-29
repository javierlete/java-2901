package com.ipartek.formacion.citas.presentacion;

import static com.ipartek.formacion.bibliotecas.Consola.*;

import java.util.Optional;

import com.ipartek.formacion.citas.accesodatos.DaoCita;
import com.ipartek.formacion.citas.accesodatos.DaoCitaTreeMap;
import com.ipartek.formacion.citas.entidades.Cita;

public class PresentacionConsola {
	private static final String FORMATO_LINEA = "| %4s | %-20s | %20s | %20s |";

	private static final int SALIR = 0;

	private static final DaoCita DAO = new DaoCitaTreeMap();
	
	public static void main(String[] args) {
		int opcion;
		
		do {
			mostrarMenu();
			opcion = elegirOpcion();
			procesarOpcion(opcion);
		} while (opcion != SALIR);
	}

	private static void mostrarMenu() {
		pl("""
				
				MENU
				====
				
				1. Listado
				2. Buscar por id
				3. Buscar por texto
				4. Insertar
				5. Modificar
				6. Borrar
				
				0. Salir
				
				""");
	}

	private static int elegirOpcion() {
		return leerInt("Elige una opción");
	}

	private static void procesarOpcion(int opcion) {
		switch(opcion) {
		case 1 -> listado();
		case 2 -> buscarPorId();
		case 3 -> buscarPorTexto();
		case 4 -> insertar();
		case 5 -> modificar();
		case 6 -> borrar();
		case 0 -> salir();
		default -> error("La opción no existe");
		}
	}

	private static void listado() {
		mostrarLineaCabecera();
		DAO.obtenerTodos().forEach(PresentacionConsola::mostrarLinea);
	}

	private static void buscarPorId() {
		mostrarFicha(DAO.obtenerPorId(leerLong("Id")));
	}

	private static void buscarPorTexto() {
		String texto = leerString("Texto");
		
		mostrarLineaCabecera();
		DAO.buscarPorTexto(texto).forEach(PresentacionConsola::mostrarLinea);
	}

	private static void insertar() {
		DAO.insertar(new Cita(leerString("Nombre"), leerLocalDateTime("Inicio"), leerLocalDateTime("Fin")));
	}

	private static void modificar() {
		DAO.modificar(new Cita(leerLong("Id"), leerString("Nombre"), leerLocalDateTime("Inicio"), leerLocalDateTime("Fin")));
	}

	private static void borrar() {
		DAO.borrar(leerLong("Id"));
	}

	private static void salir() {
		pl("Gracias por usar esta aplicación");
	}

	private static void error(String mensaje) {
		pl(mensaje);
	}
	
	private static void mostrarFicha(Optional<Cita> posibleCita) {
		posibleCita.ifPresentOrElse(cita -> pfl("""
				
				Id:     %s
				Texto:  %s
				Inicio: %s
				Fin:    %s
				
				""", cita.getId(), cita.getTexto(), cita.getInicio(), cita.getFin()), () -> pl("No se ha encontrado la cita"));
	}

	private static void mostrarLineaCabecera() {
		pfl(FORMATO_LINEA, "Id", "Texto", "Inicio", "Fin");
	}

	private static void mostrarLinea(Cita cita) {
		pfl(FORMATO_LINEA, cita.getId(), cita.getTexto(), cita.getInicio(), cita.getFin());
	}
}
