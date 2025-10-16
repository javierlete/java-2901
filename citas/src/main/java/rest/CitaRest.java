package rest;

import bibliotecas.Fabrica;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import logicanegocio.AdminNegocio;
import modelos.Cita;

@Path("/citas")
public class CitaRest {
	private static final AdminNegocio negocio = (AdminNegocio) Fabrica.obtenerObjeto("negocio.admin");
	
	@GET
	public Iterable<Cita> get() {
		return negocio.listadoCitas();
	}
}
