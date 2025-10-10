package controladores;

import java.time.LocalDateTime;
import java.util.Map;

import bibliotecas.Fabrica;
import bibliotecas.controladores.Controlador;
import bibliotecas.controladores.Ruta;
import logicanegocio.AdminNegocio;
import modelos.Cita;

@Controlador
public class AdminControlador {
	private static final AdminNegocio ADMIN_NEGOCIO = (AdminNegocio) Fabrica.obtenerObjeto("negocio.admin");

	@Ruta("/admin/listado")
	public String listado(Map<String, Object> salida) {
		salida.put("citas", ADMIN_NEGOCIO.listadoCitas());
		return "admin/listado";
	}

	@Ruta("/admin/formulario")
	public String formulario(Map<String, String> entrada, Map<String, Object> salida) {
		String sId = entrada.get("id");

		if (sId != null) {
			Long id = Long.parseLong(sId);

			var cita = ADMIN_NEGOCIO.detalleCita(id).orElse(null);

			salida.put("cita", cita);
		}

		return "admin/formulario";
	}

	@Ruta("/admin/borrar")
	public String borrar(Map<String, String> entrada, Map<String, Object> salida) {
		String sId = entrada.get("id");
		Long id = Long.parseLong(sId);

		ADMIN_NEGOCIO.bajaCita(id);

		return "redirect:/cf/admin/listado";
	}

	@Ruta("/admin/guardar")
	public String guardar(Map<String, String> entrada, Map<String, Object> salida) {
		String sId = entrada.get("id");
		String texto = entrada.get("texto");
		String sInicio = entrada.get("inicio");
		String sFin = entrada.get("fin");

		Long id = sId.isBlank() ? null : Long.parseLong(sId);
		LocalDateTime inicio = sInicio.isBlank() ? null : LocalDateTime.parse(sInicio + ":00");
		LocalDateTime fin = sFin.isBlank() ? null : LocalDateTime.parse(sFin + ":00");

		var cita = new Cita(id, texto, inicio, fin);

		if (cita.tieneErrores()) {
			salida.put("cita", cita);
			return "admin/formulario";
		}

		if (cita.getId() == null) {
			ADMIN_NEGOCIO.altaCita(cita);
		} else {
			ADMIN_NEGOCIO.modificarCita(cita);
		}

		return "redirect:/cf/admin/listado";
	}
}
