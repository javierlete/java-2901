package com.ipartek.formacion.citas.presentacion;

import static com.ipartek.formacion.bibliotecas.Consola.*;

import java.util.Optional;

import com.ipartek.formacion.bibliotecas.Fabrica;
import com.ipartek.formacion.citas.accesodatos.DaoCita;
import com.ipartek.formacion.citas.accesodatos.DaoUsuario;
import com.ipartek.formacion.citas.entidades.Cita;
import com.ipartek.formacion.citas.entidades.Usuario;

public class PresentacionConsola {
	private static final String FORMATO_LINEA = "| %4s | %-20s | %20s | %20s | %10s |";

	private static final int SALIR = 0;

	private static final DaoCita DAO_CITA = (DaoCita) Fabrica.obtenerObjeto("dao.cita");
	private static final DaoUsuario DAO_USUARIO = (DaoUsuario) Fabrica.obtenerObjeto("dao.usuario");

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
		switch (opcion) {
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
		mostrarCitaLineaCabecera();
		DAO_CITA.obtenerTodos().forEach(PresentacionConsola::mostrarCitaLinea);
	}

	private static void buscarPorId() {
		mostrarCitaFicha(DAO_CITA.obtenerPorId(leerLong("Id")));
	}

	private static void buscarPorTexto() {
		String texto = leerString("Texto");

		mostrarCitaLineaCabecera();
		DAO_CITA.buscarPorTexto(texto).forEach(PresentacionConsola::mostrarCitaLinea);
	}

	private static void insertar() {
		mostrarListadoUsuarios();
		DAO_CITA.insertar(new Cita(leerString("Texto"), leerLong("Id de usuario"), leerLocalDateTime("Inicio"),
				leerLocalDateTime("Fin")));
	}

	private static void modificar() {
		mostrarListadoUsuarios();
		DAO_CITA.modificar(new Cita(leerLong("Id"), leerString("Texto"), leerLong("Id de usuario"), leerLocalDateTime("Inicio"),
				leerLocalDateTime("Fin")));
	}

	private static void borrar() {
		DAO_CITA.borrar(leerLong("Id"));
	}

	private static void salir() {
		pl("Gracias por usar esta aplicación");
	}

	private static void error(String mensaje) {
		pl(mensaje);
	}

	private static void mostrarCitaFicha(Optional<Cita> posibleCita) {
		posibleCita.ifPresentOrElse(
				cita -> pfl("""

						Id:      %s
						Texto:   %s
						Inicio:  %s
						Fin:     %s
						Usuario: %s

						""", cita.getId(), cita.getTexto(), cita.getInicio(), cita.getFin(),
						cita.getUsuario() != null ? cita.getUsuario().getNombre() : ""),
				() -> pl("No se ha encontrado la cita"));
	}

	private static void mostrarCitaLineaCabecera() {
		pfl(FORMATO_LINEA, "Id", "Texto", "Inicio", "Fin", "Usuario");
	}

	private static void mostrarCitaLinea(Cita cita) {
		pfl(FORMATO_LINEA, cita.getId(), cita.getTexto(), cita.getInicio(), cita.getFin(),
				cita.getUsuario() != null ? cita.getUsuario().getNombre() : "");
	}

	private static void mostrarListadoUsuarios() {
		pfl("%4s %s", "Id", "Nombre");
		DAO_USUARIO.obtenerTodos().forEach(PresentacionConsola::mostrarUsuarioLinea);
	}

	private static void mostrarUsuarioLinea(Usuario usuario) {
		pfl("%4s %s", usuario.getId(), usuario.getNombre());
	}
}
