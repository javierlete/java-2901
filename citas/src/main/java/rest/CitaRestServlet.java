package rest;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import bibliotecas.Fabrica;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logicanegocio.AdminNegocio;
import modelos.Cita;

@WebServlet("/api/v1/citas/*")
public class CitaRestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Gson gson = new GsonBuilder()
			.registerTypeAdapter(LocalDateTime.class, new JsonSerializer<LocalDateTime>() {
				@Override
				public JsonElement serialize(LocalDateTime src, Type typeOfSrc, JsonSerializationContext context) {
					return new JsonPrimitive(src.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
				}
			}).registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
				@Override
				public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
						throws JsonParseException {
					return LocalDateTime.parse(json.getAsString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
				}
			}).setPrettyPrinting().create();

	private static final AdminNegocio negocio = (AdminNegocio) Fabrica.obtenerObjeto("negocio.admin");

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");

		String path = request.getPathInfo();

		if (path != null) {
			Long id = Long.parseLong(path.substring(1));

			response.getWriter().append(gson.toJson(negocio.detalleCita(id).get()));
			return;
		}

		response.getWriter().append(gson.toJson(negocio.listadoCitas()));
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");

		var cita = gson.fromJson(request.getReader(), Cita.class);

		negocio.altaCita(cita);

		response.getWriter().append(gson.toJson(cita));
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");

		String path = request.getPathInfo();

		Long id = Long.parseLong(path.substring(1));

		var cita = gson.fromJson(request.getReader(), Cita.class);

		cita.setId(id);
		
		negocio.modificarCita(cita);

		response.getWriter().append(gson.toJson(cita));
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getPathInfo();

		Long id = Long.parseLong(path.substring(1));

		negocio.bajaCita(id);
	}

}
