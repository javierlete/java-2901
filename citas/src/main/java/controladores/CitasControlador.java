package controladores;

import java.util.Map;
import java.util.Optional;

import accesodatos.DaoCita;
import accesodatos.DaoUsuario;
import bibliotecas.Fabrica;
import bibliotecas.controladores.Controlador;
import bibliotecas.controladores.Ruta;
import modelos.Cita;
import modelos.Usuario;

@Controlador
public class CitasControlador {
	private DaoCita daoCita = (DaoCita) Fabrica.obtenerObjeto("dao.cita");
	private DaoUsuario daoUsuario = (DaoUsuario) Fabrica.obtenerObjeto("dao.usuario");

	@Ruta("/citas")
	public String citas(Map<String, Object> salida) {
		salida.put("citas", daoCita.obtenerTodos());
		return "citas";
	}

	@Ruta("/detalle")
	public String detalle(Map<String, String> entrada, Map<String, Object> salida) {
		String sId = entrada.get("id");

		long id = Long.parseLong(sId);

		Optional<Cita> cita = daoCita.obtenerPorId(id);

		salida.put("cita", cita.orElse(null));

		return "detalle";
	}

	@Ruta("/login")
	public String login() {
		return "login";
	}

	@Ruta("/autenticar")
	public String autenticar(Map<String, String> entrada, Map<String, Object> salida) {
		String email = entrada.get("email");
		String password = entrada.get("password");

		var autenticado = autenticar(email, password);
		
		if (autenticado.isPresent()) {
			salida.put("sesion.usuario", autenticado.get());

			return "perfil";
		} else {
			salida.put("email", email);
			salida.put("error", "No se ha encontrado ese usuario con esa contraseña");

			return "login";
		}
	}

	private Optional<Usuario> autenticar(String email, String password) {
		Optional<Usuario> usuario = daoUsuario.obtenerPorEmail(email);
		
		if(usuario.isPresent() && usuario.get().getPassword().equals(password)) {
			return usuario;
		}
		
		return Optional.empty();
	}

	@Ruta("/logout")
	public String logout(Map<String, Object> salida) {
		salida.put("sesion", "invalidar");

		return "redirect:/cf/login";
	}
}
