package com.ipartek.formacion.citas.presentacion;

import java.io.IOException;
import java.io.PrintWriter;

import com.ipartek.formacion.bibliotecas.Fabrica;
import com.ipartek.formacion.citas.accesodatos.DaoCita;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/listado")
public class ListadoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		
		DaoCita dao = (DaoCita) Fabrica.obtenerObjeto("dao.cita");
		
		out.println("<ul>");
		
		dao.obtenerTodos().forEach(c -> out.printf("""
				<li><a href='detalle?id=%s'>%s</a> (%s): (%s => %s)</li>
				""", c.getId(), c.getTexto(), c.getUsuario() != null ? c.getUsuario().getNombre() : "", c.getInicio(), c.getFin()));
		
		out.println("</ul>");
	}
}
