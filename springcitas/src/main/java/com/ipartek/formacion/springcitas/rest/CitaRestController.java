package com.ipartek.formacion.springcitas.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ipartek.formacion.springcitas.entidades.Cita;
import com.ipartek.formacion.springcitas.servicios.AdminService;
import com.ipartek.formacion.springcitas.servicios.UsuarioService;

@RestController
@RequestMapping("/api/v2/citas")
public class CitaRestController {
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private AdminService adminService;
	
	@GetMapping
	public Iterable<Cita> obtenerTodos() {
		return usuarioService.listadoCitas();
	}
	
	@GetMapping("{id}")
	public Cita obtenerPorId(@PathVariable Long id) {
		var cita = usuarioService.detalleCita(id);
		
		if(cita.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		return cita.get();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cita insertar(@RequestBody Cita cita) {
		return adminService.altaCita(cita);
	}
	
	@PutMapping("{id}")
	public Cita modificar(@RequestBody Cita cita, @PathVariable Long id) {
		if(id != cita.getId()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No puedes modificar el id");
		}
		
		return adminService.modificarCita(cita);
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void borrar(@PathVariable Long id) {
		adminService.bajaCita(id);
	}
}
