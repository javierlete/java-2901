package pruebas;

import accesodatos.DaoRol;
import bibliotecas.Fabrica;
import modelos.Rol;

public class DaoRolPrueba {
	public static void main(String[] args) {
		DaoRol dao = (DaoRol) Fabrica.obtenerObjeto("dao.rol");
		
		dao.insertar(Rol.builder().nombre("EDITOR1").build());
		dao.insertar(Rol.builder().descripcion("Es el que te toca las pelotas").nombre("TOCAPELOTAS1").build());
		dao.insertar(Rol.builder().build());
		
		dao.obtenerTodos().forEach(System.out::println);
	}
}
