package controladores;

import java.time.LocalDateTime;
import java.util.Map;

import accesodatos.DaoCita;
import bibliotecas.Fabrica;
import bibliotecas.controladores.Controlador;
import bibliotecas.controladores.Ruta;
import modelos.Cita;

@Controlador
public class AdminControlador {
	private static final DaoCita dao = (DaoCita) Fabrica.obtenerObjeto("dao.cita");

	@Ruta("/admin/listado")
	public String listado(Map<String, Object> salida) {
		salida.put("citas", dao.obtenerTodos());
		return "admin/listado";
	}

	@Ruta("/admin/formulario")
	public String formulario(Map<String, String> entrada, Map<String, Object> salida) {
		String sId = entrada.get("id");

		if (sId != null) {
			Long id = Long.parseLong(sId);

			var cita = dao.obtenerPorId(id).orElse(null);

			salida.put("cita", cita);
		}
		
		return "admin/formulario";
	}

	@Ruta("/admin/borrar")
	public String borrar(Map<String, String> entrada, Map<String, Object> salida) {
		String sId = entrada.get("id");
		Long id = Long.parseLong(sId);

		dao.borrar(id);

		return "redirect:/cf/admin/listado";
	}

	@Ruta("/admin/guardar")
	public String guardar(Map<String, String> entrada, Map<String, Object> salida) {
		String texto = entrada.get("texto");
		String sInicio = entrada.get("inicio") + ":00";
		String sFin = entrada.get("fin") + ":00";

		LocalDateTime inicio = LocalDateTime.parse(sInicio);
		LocalDateTime fin = LocalDateTime.parse(sFin);

		var cita = new Cita(null, texto, inicio, fin);

		dao.insertar(cita);

		return "redirect:/cf/admin/listado";
	}
}
