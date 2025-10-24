package com.ipartek.formacion.springcitas.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import com.ipartek.formacion.springcitas.entidades.Cita;
import com.ipartek.formacion.springcitas.servicios.AdminService;
import com.ipartek.formacion.springcitas.servicios.UsuarioService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/")
public class IndexController {
	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private AdminService adminService;

	@GetMapping
	// @ResponseBody
	public String index(Model modelo) {
		modelo.addAttribute("citas", usuarioService.listadoCitas());
		return "index";
	}

	@GetMapping("detalle/{id}")
	public String detalle(Model modelo, @PathVariable Long id) {
		modelo.addAttribute("cita", usuarioService.detalleCita(id).orElse(null));
		return "detalle";
	}

	@GetMapping("formulario")
	public String cita(Cita cita) {
		return "formulario";
	}

	@GetMapping("formulario/{id}")
	public String citaConId(@PathVariable Long id, Model modelo) {
		var cita = usuarioService.detalleCita(id);

		System.out.println("CITA CON ID: " + cita);

		if (cita.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		modelo.addAttribute("cita", cita.get());
		return "formulario";
	}

	@PostMapping("formulario")
	public String citaPost(@Valid Cita cita, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "formulario";
		}
		
		if (cita.getId() != null) {
			adminService.modificarCita(cita);
		} else {
			adminService.altaCita(cita);
		}

		return "redirect:/";
	}
}
