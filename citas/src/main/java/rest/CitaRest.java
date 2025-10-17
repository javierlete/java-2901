package rest;

import java.net.URI;

import bibliotecas.Fabrica;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import logicanegocio.AdminNegocio;
import modelos.Cita;

@Path("/citas")
public class CitaRest {
	private static final AdminNegocio negocio = (AdminNegocio) Fabrica.obtenerObjeto("negocio.admin");

	@GET
	public Iterable<Cita> obtenerTodos() {
		return negocio.listadoCitas();
	}

	@GET
	@Path("{id}")
	public Cita obtenerPorId(@PathParam("id") Long id) {
		var cita = negocio.detalleCita(id);

		if (cita.isPresent()) {
			return cita.get();
		}

		throw new NotFoundException();
	}

	@POST
	public Response insertar(Cita cita) {
		Cita creada = negocio.altaCita(cita);

		URI location = UriBuilder.fromResource(CitaRest.class).path("{id}").resolveTemplate("id", creada.getId())
				.build();

		return Response.created(location) // <-- 201 Created
				.entity(creada) // opcional: devolver la entidad creada
				.build();
	}

	@PUT
	@Path("{id}")
	public Cita modificar(Cita cita) {
		return negocio.modificarCita(cita);
	}

	@DELETE
	@Path("{id}")
	public void borrar(@PathParam("id") Long id) {
		negocio.bajaCita(id);
	}
}
