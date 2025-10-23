package com.ipartek.formacion.springcitas.pruebas;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ipartek.formacion.springcitas.entidades.Cita;
import com.ipartek.formacion.springcitas.servicios.AdminService;
import com.ipartek.formacion.springcitas.servicios.AnonimoService;
import com.ipartek.formacion.springcitas.servicios.UsuarioService;

@Order(2)
@Component
public class ServiciosPruebas implements ApplicationRunner {
	@Autowired
	private Inicializacion init;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		init.inicializar();
	}

	@Service
	public static class Inicializacion {

		@Autowired
		private AnonimoService anonimoService;

		@Autowired
		private UsuarioService usuarioService;

		@Autowired
		private AdminService adminService;

		@Transactional
		public void inicializar() {
			anonimoService.autenticar("ail.net", "javier").ifPresentOrElse(System.out::println,
					() -> System.out.println("NO AUTENTICADO"));
			anonimoService.autenticar("javier@email.net", "javi").ifPresentOrElse(System.out::println,
					() -> System.out.println("NO AUTENTICADO"));
			anonimoService.autenticar("javier@email.net", "javier").ifPresentOrElse(System.out::println,
					() -> System.out.println("NO AUTENTICADO"));

			adminService.altaCita(Cita.builder().texto("NUEVA").inicio(LocalDateTime.of(2025, 10, 27, 8, 15))
					.fin(LocalDateTime.of(2025, 10, 27, 13, 45)).build());

			adminService.modificarCita(Cita.builder().id(3L).texto("MODIFICADA")
					.inicio(LocalDateTime.of(2025, 10, 27, 8, 15)).fin(LocalDateTime.of(2025, 10, 27, 13, 45)).build());

			adminService.bajaCita(2L);

			usuarioService.listadoCitas().forEach(System.out::println);

			usuarioService.detalleCita(2L).ifPresentOrElse(System.out::println,
					() -> System.out.println("No se ha encontrado"));

		}
	}
}
