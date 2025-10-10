package logicanegocio;

import java.util.Optional;

import accesodatos.DaoUsuario;
import bibliotecas.Fabrica;
import modelos.Usuario;

public class AnonimoNegocioImpl implements AnonimoNegocio {
	private static final DaoUsuario DAO_USUARIO = (DaoUsuario) Fabrica.obtenerObjeto("dao.usuario");
	
	@Override
	public Optional<Usuario> autenticar(String email, String password) {
		Optional<Usuario> usuario = DAO_USUARIO.obtenerPorEmail(email);

		if (usuario.isPresent() && usuario.get().getPassword().equals(password)) {
			return usuario;
		}

		return Optional.empty();
	}

}
