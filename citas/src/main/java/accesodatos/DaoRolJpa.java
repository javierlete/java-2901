package accesodatos;

import bibliotecas.accesodatos.DaoJpa;
import modelos.Rol;

public class DaoRolJpa extends DaoJpa<Rol> implements DaoRol {

	public DaoRolJpa() {
		super(Rol.class);
	}
	
}
