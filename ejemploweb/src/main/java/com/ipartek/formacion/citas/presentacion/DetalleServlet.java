package com.ipartek.formacion.citas.presentacion;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import com.ipartek.formacion.bibliotecas.Fabrica;
import com.ipartek.formacion.citas.accesodatos.DaoCita;
import com.ipartek.formacion.citas.entidades.Cita;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/detalle")
public class DetalleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		
		DaoCita dao = (DaoCita) Fabrica.obtenerObjeto("dao.cita");
		
		Optional<Cita> oCita = dao.obtenerPorId(Long.parseLong(request.getParameter("id")));
		
		if(oCita.isPresent()) {
			Cita cita = oCita.get();
		out.println("<dl>");
		
		out.printf("<dt>%s</dt>", cita.getId());
		out.printf("<dd>%s</dd>", cita.getTexto());
		out.printf("<dd>%s</dd>", cita.getUsuario() != null ? cita.getUsuario().getNombre() : "");
		
		out.println("</dl>");
		} else {
			out.println("<p>No hay cita para mostrar</p>");
		}
	}
}
