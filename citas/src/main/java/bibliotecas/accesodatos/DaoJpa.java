package bibliotecas.accesodatos;

import java.util.function.BiFunction;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class DaoJpa {
	private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("modelos");

	public static <R> R ejecutarJpa(BiFunction<EntityManager, Object[], R> codigo, Object... args) {
		EntityManager em = factory.createEntityManager();
		EntityTransaction t = em.getTransaction();

		t.begin();

		var resultado = codigo.apply(em, args);

		t.commit();

		em.close();

		return resultado;
	}
}
