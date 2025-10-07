package bibliotecas.controladores;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import bibliotecas.EscanerAnotaciones;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/cf/*")
public class ControladorFrontal extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger log = Logger.getLogger(ControladorFrontal.class.getName());

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			log.log(Level.INFO, "PATH_INFO: {0}", request.getPathInfo());

			List<Class<?>> clases = EscanerAnotaciones.findClassesWithAnnotation("controladores", Controlador.class);

			for (Class<?> clase : clases) {
				log.log(Level.FINER, "CLASE?: {0}", clase.getName());

				for (Method metodo : clase.getDeclaredMethods()) {
					log.log(Level.FINEST, "METODO?: {0}", metodo.getName());

					Ruta ruta = metodo.getAnnotation(Ruta.class);

					log.log(Level.FINEST, "@RUTA?: {0}", ruta);

					if (ruta != null && ruta.value().equals(request.getPathInfo())) {
						log.log(Level.FINE, "CLASE!: {0}", clase.getName());
						log.log(Level.FINE, "METODO!: {0}", metodo.getName());
						log.log(Level.FINE, "@RUTA!: {0}", ruta);
						
						Map<String, String> entrada = new HashMap<>();
						Map<String, Object> salida = new HashMap<>();

						Enumeration<String> parametros = request.getParameterNames();

						while (parametros.hasMoreElements()) {
							String parametro = parametros.nextElement();
							String valor = request.getParameter(parametro);

							entrada.put(parametro, valor);
						}

						log.log(Level.INFO, "ENTRADA: {0}", entrada);

						Object resultado = null;

						Object controlador = clase.getDeclaredConstructor().newInstance();
						
						switch (metodo.getParameterCount()) {
						case 2 -> resultado = metodo.invoke(controlador, entrada, salida);
						case 1 -> resultado = metodo.invoke(controlador, salida);
						case 0 -> resultado = metodo.invoke(controlador);
						}

						log.log(Level.INFO, "SALIDA: {0}", salida.toString());

						for (Entry<String, Object> par : salida.entrySet()) {
							request.setAttribute(par.getKey(), par.getValue());
						}

						log.log(Level.INFO, "RESULTADO: {0}", resultado);

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

			log.log(Level.WARNING, "No se ha encontrado la ruta " + request.getPathInfo());
			
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		} catch (ClassNotFoundException | SecurityException | IllegalAccessException | InvocationTargetException
				| InstantiationException | IllegalArgumentException | NoSuchMethodException | IOException e) {
			log.log(Level.SEVERE, "No se ha podido procesar la ruta", e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
