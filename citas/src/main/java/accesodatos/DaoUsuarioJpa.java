package accesodatos;

import java.util.Optional;

import bibliotecas.accesodatos.DaoJpa;
import modelos.Usuario;

public class DaoUsuarioJpa extends DaoJpa<Usuario> implements DaoUsuario {

	public DaoUsuarioJpa() {
		super(Usuario.class);
	}

	@Override
	public Optional<Usuario> obtenerPorEmail(String email) {
		return Optional
				.ofNullable(ejecutarJpa(em -> em.createQuery("from Usuario u where u.email = :email", Usuario.class)
						.setParameter("email", email).getSingleResultOrNull()));
	}

}
