package com.ipartek.formacion.springcitas.pruebas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ipartek.formacion.springcitas.entidades.Rol;
import com.ipartek.formacion.springcitas.entidades.Usuario;
import com.ipartek.formacion.springcitas.repositorios.RolRepository;
import com.ipartek.formacion.springcitas.repositorios.UsuarioRepository;

@Component
public class RepositoriosPruebas implements CommandLineRunner {
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private RolRepository rolRepository;

	@Override
	public void run(String... args) throws Exception {
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
	}

}
