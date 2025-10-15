package accesodatos;

import static bibliotecas.accesodatos.DaoJpa.*;

import java.util.Optional;

import modelos.Rol;

public class DaoRolJpa implements DaoRol {
	
	@Override
	public Iterable<Rol> obtenerTodos() {
		return ejecutarJpa((em, args) -> em.createQuery("from Rol", Rol.class).getResultList());
	}

	@Override
	public Optional<Rol> obtenerPorId(Long id) {
		return ejecutarJpa((em, args) -> Optional.ofNullable(em.find(Rol.class, id)), id);
	}

	@Override
	public Rol insertar(Rol rol) {
		return (Rol) ejecutarJpa((em, args) -> {
			em.persist(args[0]);
			return args[0];
		}, rol);
	}

	@Override
	public Rol modificar(Rol rol) {
		return (Rol) ejecutarJpa((em, args) -> {
			em.merge(args[0]);
			return args[0];
		}, rol);
	}

	@Override
	public void borrar(Long id) {
		ejecutarJpa((em, args) -> {
			em.remove(em.find(Rol.class, args[0]));
			return null;
		}, id);
	}
}
