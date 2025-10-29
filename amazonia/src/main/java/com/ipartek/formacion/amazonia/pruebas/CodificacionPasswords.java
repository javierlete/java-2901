package com.ipartek.formacion.amazonia.pruebas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CodificacionPasswords implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println(passwordEncoder.encode("javier"));
		System.out.println(passwordEncoder.encode("pepe"));
		System.out.println(passwordEncoder.encode("juan"));
	}

}
