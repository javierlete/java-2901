package controladores;

import java.util.Map;
import java.util.Optional;

import accesodatos.DaoCita;
import bibliotecas.Fabrica;
import bibliotecas.controladores.Controlador;
import bibliotecas.controladores.Ruta;
import modelos.Cita;

@Controlador
public class CitasControlador {
	private DaoCita dao = (DaoCita) Fabrica.obtenerObjeto("dao.cita");
	
	@Ruta("/citas")
	public String citas(Map<String, Object> salida) {
		salida.put("citas", dao.obtenerTodos());
		return "citas";
	}
	
	@Ruta("/detalle")
	public String detalle(Map<String, String> entrada, Map<String, Object> salida) {
		String sId = entrada.get("id");
		
		long id = Long.parseLong(sId);
		
		Optional<Cita> cita = dao.obtenerPorId(id);
		
		salida.put("cita", cita.orElse(null));
		
		return "detalle";
	}
}
