package pruebas;

import accesodatos.DaoRol;
import accesodatos.DaoUsuario;
import bibliotecas.Fabrica;
import modelos.Rol;
import modelos.Usuario;

public class DaoUsuarioJpaPrueba {
	public static void main(String[] args) {
		DaoRol daoRol = (DaoRol) Fabrica.obtenerObjeto("dao.rol");
		
		Rol admin = daoRol.insertar(Rol.builder().nombre("ADMINISTRADOR").build());
		daoRol.insertar(Rol.builder().nombre("USUARIO").build());
		
		System.out.println(admin);
		
		DaoUsuario daoUsuario = (DaoUsuario) Fabrica.obtenerObjeto("dao.usuario");
		
		Usuario javier = Usuario.builder().email("javier@email.net").password("javier").nombre("Javier").rol(admin).build();
		System.out.println(javier);
		
		Usuario usuarioInsertado = daoUsuario.insertar(javier);
		daoUsuario.insertar(Usuario.builder().email("pepe@email.net").password("pepe").nombre("Pepe").rol(daoRol.obtenerPorId(2L).get()).build());
		daoUsuario.insertar(Usuario.builder().email("juan@email.net").password("juan").nombre("Juan").rol(Rol.builder().id(2L).build()).build());
		
		System.out.println(usuarioInsertado);
		
		daoUsuario.obtenerTodos().forEach(u -> System.out.printf("%s, %s, %s, %s, %s, %s, %s\n", u.getId(), u.getEmail(), u.getPassword(), u.getNombre(), u.getRol().getId(), u.getRol().getNombre(), u.getRol().getDescripcion()));

		System.out.println("\nLISTADO COMPLETO DE USUARIOS\n");
		
		daoUsuario.obtenerTodos().forEach(System.out::println);
		
		System.out.println("Obtención de rol");
		
		Rol usuarios = daoRol.obtenerPorId(2L).get();
		
		System.out.println("Obtención de usuario de un rol");
		
		usuarios.getUsuarios().forEach(System.out::println);
	}
}
