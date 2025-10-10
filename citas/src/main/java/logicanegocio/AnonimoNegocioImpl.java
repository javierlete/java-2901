package logicanegocio;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import accesodatos.DaoUsuario;
import bibliotecas.Fabrica;
import modelos.Usuario;

public class AnonimoNegocioImpl implements AnonimoNegocio {
	private static final Logger log = Logger.getLogger(AnonimoNegocioImpl.class.getName());
	
	private static final DaoUsuario DAO_USUARIO = (DaoUsuario) Fabrica.obtenerObjeto("dao.usuario");
	
	@Override
	public Optional<Usuario> autenticar(String email, String password) {
		Optional<Usuario> usuario = DAO_USUARIO.obtenerPorEmail(email);

		if(usuario.isEmpty()) {
			log.warning("USUARIO NO ENCONTRADO");
			return Optional.empty();
		}
		
		boolean passwordCorrecta = usuario.get().getPassword().equals(password);

		if(!passwordCorrecta) {
			log.log(Level.WARNING, "PASSWORD INCORRECTA para el usuario {0}", usuario.get().getEmail());
		}
		
		if (usuario.isPresent() && passwordCorrecta) {
			log.log(Level.FINEST,"Usuario logueado correctamente: {0}", usuario.get().getEmail());
			return usuario;
		}

		return Optional.empty();
	}

}
