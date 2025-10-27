package com.ipartek.formacion.amazonia.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	@GetMapping
	public String index() {
		return "index";
	}
	
	@GetMapping("detalle")
	public String detalle() {
		return "detalle";
	}
}
