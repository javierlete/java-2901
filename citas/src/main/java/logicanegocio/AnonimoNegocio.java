package logicanegocio;

import java.util.Optional;

import modelos.Usuario;

public interface AnonimoNegocio {
	Optional<Usuario> autenticar(String email, String password);
}
