package com.ipartek.formacion.citas.presentacion.controladores;

import java.io.IOException;

import com.ipartek.formacion.bibliotecas.Fabrica;
import com.ipartek.formacion.citas.accesodatos.DaoCita;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/citas/detalle")
public class DetalleControladorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Extraer información de la petición
		var sId = request.getParameter("id");
		
		// Convertir la información recibida
		var id = Long.parseLong(sId);
		
		// Empaquetar en objetos
		// Ejecutar lógica de negocio
		var dao = (DaoCita) Fabrica.obtenerObjeto("dao.cita");

		var cita = dao.obtenerPorId(id);

		// Empaquetar objetos para la siguiente vista
		request.setAttribute("cita", cita.orElse(null));

		// Mostrar la siguiente vista
		request.getRequestDispatcher("/WEB-INF/vistas/detalle.jsp").forward(request, response);

	}
}
