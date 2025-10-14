package pruebas;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import modelos.Rol;

public class JpaPrueba {
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("modelos");
		EntityManager em = factory.createEntityManager();
		
		em.getTransaction().begin();
		
		em.persist(Rol.builder().nombre("ADMINISTRADOR").build());
		em.persist(Rol.builder().nombre("USUARIO").build());
		
		List<Rol> roles = em.createQuery("from Rol", Rol.class).getResultList();
		
		roles.forEach(System.out::println);
		
		em.getTransaction().commit();
		
		em.close();
		factory.close();
	}
}
