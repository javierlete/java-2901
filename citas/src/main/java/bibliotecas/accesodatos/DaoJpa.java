package bibliotecas.accesodatos;

import java.util.Optional;
import java.util.function.Function;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class DaoJpa<T> {
	private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("modelos");

	private final Class<T> clase;

	public DaoJpa(Class<T> clase) {
		this.clase = clase;
	}

	public static <R> R ejecutarJpa(Function<EntityManager, R> codigo) {
		EntityManager em = factory.createEntityManager();
		EntityTransaction t = em.getTransaction();

		t.begin();

		var resultado = codigo.apply(em);

		t.commit();

		em.close();

		return resultado;
	}

	public Iterable<T> obtenerTodos() {
		return ejecutarJpa(em -> em.createQuery("from " + clase.getName(), clase).getResultList());
	}

	public Optional<T> obtenerPorId(Long id) {
		return ejecutarJpa(em -> Optional.ofNullable(em.find(clase, id)));
	}

	public T insertar(T objeto) {
		return ejecutarJpa(em -> {
			em.persist(objeto);
			return objeto;
		});
	}

	public T modificar(T objeto) {
		return ejecutarJpa(em -> {
			em.merge(objeto);
			return objeto;
		});
	}

	public void borrar(Long id) {
		ejecutarJpa(em -> {
			em.remove(em.find(clase, id));
			return null;
		});
	}
}
