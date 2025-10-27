package com.ipartek.formacion.amazonia.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ipartek.formacion.amazonia.servicios.AdministradorService;

@Controller
@RequestMapping("admin")
public class AdminController {
	@Autowired
	private AdministradorService administradorService;
	
	@GetMapping
	public String index(Model modelo) {
		modelo.addAttribute("productos", administradorService.listadoProductos());
		
		return "admin/index";
	}
}
