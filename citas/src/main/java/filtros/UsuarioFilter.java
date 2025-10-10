package filtros;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelos.Usuario;

@WebFilter({ "/cf/citas", "/cf/detalle" })
public class UsuarioFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		HttpSession session = req.getSession();

		Usuario usuario = (Usuario) session.getAttribute("usuario");

		if (usuario == null || !"USUARIO".equals(usuario.getRol().getNombre()) && !"ADMINISTRADOR".equals(usuario.getRol().getNombre())) {
			res.sendRedirect(req.getContextPath() + "/cf/login");
			return;
		}
		
		chain.doFilter(request, response);
	}

}
