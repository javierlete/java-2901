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

						ejecutarMetodo(request, response, clase, metodo);

						return;
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

	private void ejecutarMetodo(HttpServletRequest request, HttpServletResponse response, Class<?> clase, Method metodo)
			throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException,
			IOException, ServletException {
		// Extraer información de la petición
		Map<String, Object> entrada = recogerParametros(request);

		log.log(Level.INFO, "ENTRADA: {0}", entrada);

		// Convertir la información recibida
		// Empaquetar en objetos
		// Ejecutar lógica de negocio
		Map<String, Object> salida = new HashMap<>();
		String resultado = (String) invocarMetodo(clase, metodo, entrada, salida);

		log.log(Level.INFO, "SALIDA: {0}", salida.toString());

		// Empaquetar objetos para la siguiente vista
		crearAtributos(request, salida);

		log.log(Level.INFO, "RESULTADO: {0}", resultado);

		if (resultado != null && resultado instanceof String vista) {
			// Mostrar la siguiente vista
			saltarAVista(request, response, vista);
		} else {
			log.log(Level.SEVERE, "No se ha proporcionado vista");
		}

		return;
	}

	private void saltarAVista(HttpServletRequest request, HttpServletResponse response, String vista)
			throws IOException, ServletException {
		if (vista.startsWith("redirect:")) {
			response.sendRedirect(request.getContextPath() + vista.replace("redirect:", ""));
		} else {
			request.getRequestDispatcher("/WEB-INF/vistas/" + vista + ".jsp").forward(request, response);
		}
	}

	private Object invocarMetodo(Class<?> clase, Method metodo, Map<String, Object> entrada, Map<String, Object> salida)
			throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Object resultado;
		Object controlador = clase.getDeclaredConstructor().newInstance();

		int numeroArgumentos = metodo.getParameterCount();

		resultado = switch (numeroArgumentos) {
		case 2 -> metodo.invoke(controlador, entrada, salida);
		case 1 -> metodo.invoke(controlador, salida);
		case 0 -> metodo.invoke(controlador);
		default -> throw new IllegalArgumentException("No soportamos " + numeroArgumentos + " argumentos");
		};
		return resultado;
	}

	private void crearAtributos(HttpServletRequest request, Map<String, Object> salida) {
		for (Entry<String, Object> par : salida.entrySet()) {
			if ("sesion".equals(par.getKey()) && "invalidar".equals(par.getValue())) {
				request.getSession().invalidate();
			} else if (par.getKey().startsWith("sesion.")) {
				request.getSession().setAttribute(par.getKey().replace("sesion.", ""), par.getValue());
			} else {
				request.setAttribute(par.getKey(), par.getValue());
			}
		}
	}

	private Map<String, Object> recogerParametros(HttpServletRequest request) {
		Map<String, Object> entrada;
		entrada = new HashMap<>();

		Enumeration<String> parametros = request.getParameterNames();

		while (parametros.hasMoreElements()) {
			String parametro = parametros.nextElement();
			String valor = request.getParameter(parametro);

			entrada.put(parametro, valor);
		}

		Enumeration<String> atributos = request.getSession().getAttributeNames();

		while (atributos.hasMoreElements()) {
			String atributo = atributos.nextElement();
			Object valor = request.getSession().getAttribute(atributo);

			entrada.put("sesion." + atributo, valor);
		}

		return entrada;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
