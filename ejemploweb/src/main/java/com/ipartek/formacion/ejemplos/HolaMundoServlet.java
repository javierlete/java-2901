package com.ipartek.formacion.ejemplos;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/hola")
public class HolaMundoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		String nombre = request.getParameter("nombre"); // http://localhost:8080/ejemploweb/hola?nombre=loquesea

		if(nombre == null) {
			nombre = "DESCONOCIDO";
		}
		
		out.printf("""
				<h1>Hola %s</h1>
				""", nombre);
	}

}
