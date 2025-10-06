package controladores;

import java.util.Map;

import accesodatos.DaoCita;
import bibliotecas.Fabrica;
import bibliotecas.controladores.Controlador;
import bibliotecas.controladores.Ruta;

@Controlador
public class CitasControlador {
	private DaoCita dao = (DaoCita) Fabrica.obtenerObjeto("dao.cita");
	
	@Ruta("/citas")
	public String citas(Map<String, Object> salida) {
		salida.put("citas", dao.obtenerTodos());
		return "citas";
	}
}
