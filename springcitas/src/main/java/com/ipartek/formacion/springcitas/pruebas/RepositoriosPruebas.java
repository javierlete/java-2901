package com.ipartek.formacion.springcitas.pruebas;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ipartek.formacion.springcitas.entidades.Cita;
import com.ipartek.formacion.springcitas.entidades.Rol;
import com.ipartek.formacion.springcitas.entidades.Usuario;
import com.ipartek.formacion.springcitas.repositorios.CitaRepository;
import com.ipartek.formacion.springcitas.repositorios.RolRepository;
import com.ipartek.formacion.springcitas.repositorios.UsuarioRepository;

@Order(1)
@Component
public class RepositoriosPruebas implements ApplicationRunner {
	@Autowired
	private Inicializacion init;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		init.inicializacion();
	}
	
	@Service
	public static class Inicializacion {
		@Autowired
		private UsuarioRepository usuarioRepository;

		@Autowired
		private RolRepository rolRepository;

		@Autowired
		private CitaRepository citaRepository;
		
		@Transactional
		public void inicializacion() {
			var admin = Rol.builder().nombre("ADMIN").build();
			var user = Rol.builder().nombre("USER").build();

			rolRepository.save(admin);
			rolRepository.save(user);

			usuarioRepository.save(
					Usuario.builder().nombre("Javier").email("javier@email.net").password("javier").rol(admin).build());
			usuarioRepository
					.save(Usuario.builder().nombre("Pepe").email("pepe@email.net").password("pepe").rol(user).build());
			usuarioRepository
					.save(Usuario.builder().nombre("Juan").email("juan@email.net").password("juan").rol(user).build());

			usuarioRepository.findAll().forEach(System.out::println);

			System.out.println();

			usuarioRepository.findByEmail("pepe@email.net").ifPresentOrElse(System.out::println,
					() -> System.out.println("No se ha encontrado"));

			for (int i = 0; i < 5; i++) {
				citaRepository
						.save(Cita.builder().texto("Cita " + i).inicio(LocalDateTime.of(2025, 10, 20, 8, 15).plusDays(i))
								.fin(LocalDateTime.of(2025, 10, 20, 13, 45).plusDays(i)).build());
			}

			Page<Cita> pagina;
			Pageable pageable = Pageable.ofSize(2);
			
			int numeroPagina = 0;
			
			do {
				pagina = citaRepository.findByInicioBetween(pageable.withPage(numeroPagina++), LocalDateTime.of(2025, 10, 21, 0, 0),
						LocalDateTime.of(2025, 10, 23, 23, 59));
				
				System.out.println(pagina.getNumber() + 1);

				pagina.stream().forEach(System.out::println);
			} while(pagina.hasNext());
		}
	}

}
