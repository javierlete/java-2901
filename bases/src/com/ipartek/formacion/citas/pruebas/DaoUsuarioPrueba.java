package com.ipartek.formacion.citas.pruebas;

import com.ipartek.formacion.bibliotecas.Fabrica;
import com.ipartek.formacion.citas.accesodatos.DaoUsuario;

public class DaoUsuarioPrueba {
	public static void main(String[] args) {
		DaoUsuario dao = (DaoUsuario) Fabrica.obtenerObjeto("dao.usuario");

//		dao.insertar(new Usuario(null, "Juan"));

		dao.obtenerTodos().forEach(System.out::println);

		dao.obtenerPorId(2L).ifPresentOrElse(System.out::println,
				() -> System.out.println("No se ha encontrado el usuario"));
	}
}
