package com.ipartek.formacion.amazonia.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ipartek.formacion.amazonia.servicios.AnonimoService;
import com.ipartek.formacion.amazonia.sesion.Carrito;

@Controller
public class IndexController {
	@Autowired
	private AnonimoService anonimoService;

	@GetMapping
	public String index(Model modelo, Long idCategoria) {
		modelo.addAttribute("productos", anonimoService.listadoProductos(idCategoria));

		return "index";
	}

	@GetMapping("detalle")
	public String detalle(Long id, Model modelo) {
		modelo.addAttribute("producto", anonimoService.detalleProducto(id));

		return "detalle";
	}
	
	@GetMapping("carrito")
	public String carrito(Long id, Carrito carrito) {
		carrito.agregarProducto(anonimoService.detalleProducto(id));
		
		return "redirect:/";
	}
}
