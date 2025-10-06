package com.ipartek.formacion.citas.presentacion.controladores.admin;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

import com.ipartek.formacion.bibliotecas.Ruta;
import com.ipartek.formacion.citas.entidades.Cita;
import com.ipartek.formacion.citas.presentacion.controladores.Globales;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/*")
public class AdminListadoControladorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(request.getPathInfo());
		
		for(Method metodo: getClass().getDeclaredMethods()) {
			System.out.println(metodo.getName());
			
			Ruta ruta = metodo.getAnnotation(Ruta.class);
			
			System.out.println(ruta);
			
			if(ruta != null && ruta.value().equals(request.getPathInfo())) {
				try {
					metodo.invoke(this, request, response);
				} catch (IllegalAccessException | InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		
		response.setStatus(HttpServletResponse.SC_NOT_FOUND);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	@Ruta("/guardar")
	private void guardar(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("GUARDAR");

		// Recojer datos de la petición
		var sId = request.getParameter("id");
		var sIdUsuario = request.getParameter("id-usuario");
		var texto = request.getParameter("texto");
		var sInicio = request.getParameter("inicio");
		var sFin = request.getParameter("fin");

		// Convertir los que sea necesario
		var id = sId.isBlank() ? null : Long.parseLong(sId);
		var idUsuario = "0".equals(sIdUsuario) ? null : Long.parseLong(sIdUsuario);
		var inicio = LocalDateTime.parse(sInicio);
		var fin = LocalDateTime.parse(sFin);

		// Crear objeto con los datos
		var cita = new Cita(id, texto, idUsuario, inicio, fin);

		// Lógica de negocio
		if (cita.getId() == null) {
			Globales.DAO_CITA.insertar(cita);
		} else {
			Globales.DAO_CITA.modificar(cita);
		}

		// Empaquetar objetos para la siguiente vista
		// Saltar a la siguiente vista
		response.sendRedirect("listado");
	}

	@Ruta("/editar")
	private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("EDITAR");

		// Recojer datos de la petición
		var sId = request.getParameter("id");

		// Convertir los que sea necesario
		var id = Long.parseLong(sId);

		// Crear objeto con los datos
		// Lógica de negocio
		var cita = Globales.DAO_CITA.obtenerPorId(id);

		// Empaquetar objetos para la siguiente vista
		request.setAttribute("cita", cita.get());

		// Saltar a la siguiente vista

		anyadir(request, response);
	}

	@Ruta("/borrar")
	private void borrar(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// Recojer datos de la petición
		String sId = request.getParameter("id");

		// Convertir los que sea necesario
		Long id = Long.parseLong(sId);

		// Crear objeto con los datos
		// Lógica de negocio
		Globales.DAO_CITA.borrar(id);

		// Empaquetar objetos para la siguiente vista
		// Saltar a la siguiente vista
		response.sendRedirect("listado");
	}

	@Ruta("/anyadir")
	private void anyadir(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("AÑADIR");
		// Recojer datos de la petición
		// Convertir los que sea necesario
		// Crear objeto con los datos
		// Lógica de negocio
		var usuarios = Globales.DAO_USUARIO.obtenerTodos();
		// Empaquetar objetos para la siguiente vista
		request.setAttribute("usuarios", usuarios);
		// Saltar a la siguiente vista
		request.getRequestDispatcher("/WEB-INF/vistas/admin/formulario.jsp").forward(request, response);
	}

	@Ruta("/listado")
	private void listado(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recojer datos de la petición
		// Convertir los que sea necesario
		// Crear objeto con los datos
		// Lógica de negocio
		var citas = Globales.DAO_CITA.obtenerTodos();

		// Empaquetar objetos para la siguiente vista
		request.setAttribute("citas", citas);

		// Saltar a la siguiente vista
		request.getRequestDispatcher("/WEB-INF/vistas/admin/listado.jsp").forward(request, response);
	}
}
