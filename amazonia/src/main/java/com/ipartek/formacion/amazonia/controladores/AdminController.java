package com.ipartek.formacion.amazonia.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ipartek.formacion.amazonia.entidades.Producto;
import com.ipartek.formacion.amazonia.servicios.AdministradorService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("admin")
public class AdminController {

	@Autowired
	private AdministradorService administradorService;

	@GetMapping
	public String index(Model modelo) {
		modelo.addAttribute("categorias", administradorService.listadoCategorias());
		modelo.addAttribute("productos", administradorService.listadoProductos());

		return "admin/index";
	}

	@GetMapping("producto")
	public String producto(Model modelo, Long id) {
		Producto producto = Producto.builder().build();

		if (id != null) {
			producto = administradorService.detalleProducto(id);
		}

		modelo.addAttribute("producto", producto);
		modelo.addAttribute("categorias", administradorService.listadoCategorias());

		return "admin/producto";
	}

	@PostMapping("producto")
	public String productoPost(@Valid Producto producto, BindingResult bindingResult, Model modelo) {
		if (bindingResult.hasErrors()) {
			modelo.addAttribute("categorias", administradorService.listadoCategorias());

			return "admin/producto";
		}

		if (producto.getId() != null) {
			administradorService.modificacionProducto(producto);
		} else {
			administradorService.altaProducto(producto);
		}

		return "redirect:/admin";
	}

	@GetMapping("borrar")
	public String borrar(Long id) {
		administradorService.bajaProducto(id);

		return "redirect:/admin";
	}
}
