package com.ipartek.formacion.springcitas.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ipartek.formacion.springcitas.entidades.Usuario;
import com.ipartek.formacion.springcitas.servicios.AnonimoService;

@RestController
@RequestMapping("/api/v2")
public class AnonimoRestController {
	@Autowired
	private AnonimoService anonimoService;
	
	@GetMapping("/autenticar")
	public Usuario autenticar(String email, String password) {
		Optional<Usuario> usuario = anonimoService.autenticar(email, password);
		
		if(usuario.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		return usuario.get();
	}
}
