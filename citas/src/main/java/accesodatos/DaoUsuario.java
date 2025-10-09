package accesodatos;

import java.util.Optional;

import bibliotecas.accesodatos.Dao;
import modelos.Usuario;

public interface DaoUsuario extends Dao<Usuario> {

	Optional<Usuario> obtenerPorEmail(String email);

}
