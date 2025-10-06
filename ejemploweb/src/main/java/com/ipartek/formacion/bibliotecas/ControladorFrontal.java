package com.ipartek.formacion.bibliotecas;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/cf/*")
public class ControladorFrontal extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			System.out.println(request.getPathInfo());

			List<Class<?>> clases = EscanerAnotaciones.findClassesWithAnnotation("controladores", Controlador.class);

			for (Class<?> clase : clases) {
				System.out.println(clase.getName());

				for (Method metodo : clase.getDeclaredMethods()) {
					System.out.println(metodo.getName());

					Ruta ruta = metodo.getAnnotation(Ruta.class);

					System.out.println(ruta);

					if (ruta != null && ruta.value().equals(request.getPathInfo())) {
						Map<String, String> entrada = new HashMap<>();
						Map<String, Object> salida = new HashMap<>();

						Enumeration<String> parametros = request.getParameterNames();

						while (parametros.hasMoreElements()) {
							String parametro = parametros.nextElement();
							String valor = request.getParameter(parametro);

							entrada.put(parametro, valor);
						}

						System.out.println(entrada);

						Object resultado = null;

						Object controlador = clase.getDeclaredConstructor().newInstance();
						
						switch (metodo.getParameterCount()) {
						case 2 -> resultado = metodo.invoke(controlador, entrada, salida);
						case 1 -> resultado = metodo.invoke(controlador, salida);
						case 0 -> resultado = metodo.invoke(controlador);
						}

						System.out.println(salida);

						for (Entry<String, Object> par : salida.entrySet()) {
							request.setAttribute(par.getKey(), par.getValue());
						}

						System.out.println(resultado);

						if (resultado != null && resultado instanceof String vista) {
							if (vista.startsWith("redirect:")) {
								response.sendRedirect(request.getContextPath() + vista.replace("redirect:", ""));
							} else {
								request.getRequestDispatcher("/WEB-INF/vistas/" + vista + ".jsp").forward(request,
										response);
							}
						}
					}
				}
			}

			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		} catch (ClassNotFoundException | SecurityException | IllegalAccessException | InvocationTargetException
				| InstantiationException | IllegalArgumentException | NoSuchMethodException | IOException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
