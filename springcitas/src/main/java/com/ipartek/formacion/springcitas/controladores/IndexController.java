package com.ipartek.formacion.springcitas.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ipartek.formacion.springcitas.servicios.UsuarioService;

@Controller
@RequestMapping("/")
public class IndexController {
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
	// @ResponseBody
	public String index(Model modelo) {
		modelo.addAttribute("citas", usuarioService.listadoCitas());
		return "index";
	}
	
	@GetMapping("detalle/{id}")
	public String detalle(Model modelo, @PathVariable Long id) {
		modelo.addAttribute("cita", usuarioService.detalleCita(id).get());
		return "detalle";
	}
	
}
