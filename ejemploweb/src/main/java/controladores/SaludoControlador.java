package controladores;

import java.io.IOException;
import java.util.Map;

import com.ipartek.formacion.bibliotecas.Controlador;
import com.ipartek.formacion.bibliotecas.Ruta;

@Controlador
public class SaludoControlador {
	@Ruta("/hola")
	public String hola(Map<String, String> entrada, Map<String, Object> salida) throws IOException {
		salida.put("nombre", entrada.get("nombre"));
		return "saludo";
	}
	
	@Ruta("/admin")
	public String admin() {
		return "redirect:/admin/listado";
	}
}
