package controladores;

import java.util.Map;
import java.util.Optional;

import bibliotecas.Fabrica;
import bibliotecas.controladores.Controlador;
import bibliotecas.controladores.Ruta;
import logicanegocio.AnonimoNegocio;
import logicanegocio.UsuarioNegocio;
import modelos.Cita;

@Controlador
public class CitasControlador {
	private static final AnonimoNegocio anonimoNegocio = (AnonimoNegocio) Fabrica.obtenerObjeto("negocio.anonimo");
	private static final UsuarioNegocio usuarioNegocio = (UsuarioNegocio) Fabrica.obtenerObjeto("negocio.usuario");
	
	@Ruta("/citas")
	public String citas(Map<String, Object> salida) {
		salida.put("citas", usuarioNegocio.listadoCitas());
		return "citas";
	}

	@Ruta("/detalle")
	public String detalle(Map<String, String> entrada, Map<String, Object> salida) {
		String sId = entrada.get("id");

		long id = Long.parseLong(sId);

		Optional<Cita> cita = usuarioNegocio.detalleCita(id);

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

		var autenticado = anonimoNegocio.autenticar(email, password);
		
		if (autenticado.isPresent()) {
			salida.put("sesion.usuario", autenticado.get());

			return "perfil";
		} else {
			salida.put("email", email);
			salida.put("error", "No se ha encontrado ese usuario con esa contraseña");

			return "login";
		}
	}

	@Ruta("/logout")
	public String logout(Map<String, Object> salida) {
		salida.put("sesion", "invalidar");

		return "redirect:/cf/login";
	}
}
