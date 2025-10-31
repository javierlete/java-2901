package com.ipartek.formacion.amazonia.controladores.advices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.ipartek.formacion.amazonia.servicios.AnonimoService;
import com.ipartek.formacion.amazonia.sesion.Carrito;

@ControllerAdvice
public class ComunControllerAdvice {
	@Autowired
	private Carrito carrito;
	
	@Autowired
	private AnonimoService anonimoService;
	
	@ModelAttribute
	private void modeloGlobal(Model modelo) {
		modelo.addAttribute("categorias", anonimoService.listadoCategorias());
		modelo.addAttribute("carrito", carrito);
	}
}
