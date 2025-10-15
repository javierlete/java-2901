package accesodatos;

import java.util.Optional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import modelos.Rol;

public class DaoRolJpa implements DaoRol {
	private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("modelos");

	@Override
	public Iterable<Rol> obtenerTodos() {
		Iterable<Rol> roles = null;

		EntityManager em = factory.createEntityManager();
		EntityTransaction t = em.getTransaction();

		t.begin();

		roles = em.createQuery("from Rol", Rol.class).getResultList();

		t.commit();

		em.close();

		return roles;
	}

	@Override
	public Optional<Rol> obtenerPorId(Long id) {
		Optional<Rol> rol = Optional.empty();

		EntityManager em = factory.createEntityManager();
		EntityTransaction t = em.getTransaction();

		t.begin();

		rol = Optional.ofNullable(em.find(Rol.class, id));

		t.commit();

		em.close();

		return rol;
	}

	@Override
	public Rol insertar(Rol rol) {
		EntityManager em = factory.createEntityManager();
		EntityTransaction t = em.getTransaction();
		
		t.begin();
		
		em.persist(rol);
		
		t.commit();
		
		em.close();
		
		return rol;
	}

	@Override
	public Rol modificar(Rol rol) {
		EntityManager em = factory.createEntityManager();
		EntityTransaction t = em.getTransaction();
		
		t.begin();
		
		em.merge(rol);
		
		t.commit();
		
		em.close();
		
		return rol;
	}

	@Override
	public void borrar(Long id) {
		EntityManager em = factory.createEntityManager();
		EntityTransaction t = em.getTransaction();
		
		t.begin();
		
		em.remove(em.find(Rol.class, id));
		
		t.commit();
		
		em.close();
	}

}
